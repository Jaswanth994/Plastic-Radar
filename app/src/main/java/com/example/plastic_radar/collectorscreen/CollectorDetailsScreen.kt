package com.example.plastic_radar.collectorscreen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
//import androidx.compose.material3.icons.Icons
//import androidx.compose.material3.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.delay

@Composable
fun CollectorDetailsScreen(navController: NavHostController) {
    var username by remember { mutableStateOf(TextFieldValue()) }
    var dob by remember { mutableStateOf(TextFieldValue()) }
    var aadharNum by remember { mutableStateOf(TextFieldValue()) }
    var phoneNum by remember { mutableStateOf(TextFieldValue()) }
    var otherDetails by remember { mutableStateOf(TextFieldValue()) }

    var usernameError by remember { mutableStateOf(false) }
    var dobError by remember { mutableStateOf(false) }
    var aadharNumError by remember { mutableStateOf(false) }
    var phoneNumError by remember { mutableStateOf(false) }
    var registrationSuccess by remember { mutableStateOf(false) }

    // Firestore instance
    val db = FirebaseFirestore.getInstance()
    val currentUserUid = FirebaseAuth.getInstance().currentUser?.uid

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Top
    ) {
        // Header
        Text(
            text = "Collector Registration",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            color = MaterialTheme.colorScheme.primary
        )

        // Form Fields
        OutlinedTextField(
            value = username,
            onValueChange = {
                username = it
                usernameError = it.text.isEmpty()
            },
            label = { Text("Username") },
            leadingIcon = { Icon(Icons.Filled.Person, contentDescription = "Username Icon") },
            isError = usernameError,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)
        )
        if (usernameError) {
            Text("Please enter a username", color = MaterialTheme.colorScheme.error)
        }

        OutlinedTextField(
            value = dob,
            onValueChange = {
                dob = it
                dobError = it.text.isEmpty()
            },
            label = { Text("Date of Birth") },
            leadingIcon = { Icon(Icons.Filled.DateRange, contentDescription = "Date of Birth Icon") },
            isError = dobError,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)
        )
        if (dobError) {
            Text("Please enter your date of birth", color = MaterialTheme.colorScheme.error)
        }

        OutlinedTextField(
            value = aadharNum,
            onValueChange = {
                aadharNum = it
                aadharNumError = it.text.length != 12 || it.text.any { ch -> !ch.isDigit() }
            },
            label = { Text("Aadhar Number") },
            leadingIcon = { Icon(Icons.Filled.CreditCard, contentDescription = "Aadhar Number Icon") },
            isError = aadharNumError,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
            modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)
        )
        if (aadharNumError) {
            Text("Aadhar number should be 12 digits", color = MaterialTheme.colorScheme.error)
        }

        OutlinedTextField(
            value = phoneNum,
            onValueChange = {
                phoneNum = it
                phoneNumError = it.text.length != 10 || it.text.any { ch -> !ch.isDigit() }
            },
            label = { Text("Phone Number") },
            leadingIcon = { Icon(Icons.Filled.Phone, contentDescription = "Phone Number Icon") },
            isError = phoneNumError,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone, imeAction = ImeAction.Next),
            modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)
        )
        if (phoneNumError) {
            Text("Phone number should be 10 digits", color = MaterialTheme.colorScheme.error)
        }

        OutlinedTextField(
            value = otherDetails,
            onValueChange = { otherDetails = it },
            label = { Text("Other Details") },
            leadingIcon = { Icon(Icons.Filled.Description, contentDescription = "Other Details Icon") },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )

        // Register Button
        Button(
            onClick = {
                usernameError = username.text.isEmpty()
                dobError = dob.text.isEmpty()
                aadharNumError = aadharNum.text.length != 12 || aadharNum.text.any { ch -> !ch.isDigit() }
                phoneNumError = phoneNum.text.length != 10 || phoneNum.text.any { ch -> !ch.isDigit() }

                if (!usernameError && !dobError && !aadharNumError && !phoneNumError) {
                    val collectorData = hashMapOf(
                        "username" to username.text,
                        "dob" to dob.text,
                        "aadharNum" to aadharNum.text,
                        "phoneNum" to phoneNum.text,
                        "otherDetails" to otherDetails.text
                    )

                    currentUserUid?.let { uid ->
                        db.collection("collectors")
                            .document(uid)
                            .set(collectorData)
                            .addOnSuccessListener {
                                registrationSuccess = true
                                // Navigate to Collector Screen on successful registration
                                navController.navigate("collector") {
                                    popUpTo(navController.graph.startDestinationId) {
                                        inclusive = true
                                    }
                                }
                            }
                            .addOnFailureListener { e ->
                                // Handle the error
                                println("Error adding document: $e")
                            }
                    }
                }
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(top = 16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text("Register", color = MaterialTheme.colorScheme.onPrimary)
        }

        if (registrationSuccess) {
            Text(
                text = "Registration Successful! You are now a Collector !!",
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(top = 25.dp)
            )
            LaunchedEffect(Unit) {
                delay(2000)  // Delay for 2 seconds
                navController.navigate("collector")  // Navigate after delay
            }
        }
    }
}
