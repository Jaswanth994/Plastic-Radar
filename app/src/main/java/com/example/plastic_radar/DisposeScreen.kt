package com.example.plastic_radar

import android.annotation.SuppressLint
import  com.google.firebase.firestore.FirebaseFirestore
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import android.app.DatePickerDialog
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.zIndex
import java.util.Calendar

data class Address(
    val name: String = "",
    val phoneNumber: String = "",
    val addressName: String = "",
    val city: String = "",
    val selectedPlastics: List<String> = listOf(),
    val quantity: List<String> = listOf(),
    val scheduleDate: String = "",
)

fun saveAddress(address: Address, onSuccess: () -> Unit, onError: (Exception) -> Unit) {
    val db = FirebaseFirestore.getInstance()
    db.collection("addresses")
        .add(address)
        .addOnSuccessListener {
            onSuccess()
        }
        .addOnFailureListener { e ->
            onError(e)
        }
}

fun getAddresses(onSuccess: (List<Address>) -> Unit, onError: (Exception) -> Unit) {
    val db = FirebaseFirestore.getInstance()
    db.collection("addresses")
        .get()
        .addOnSuccessListener { result ->
            val addresses = result.map { document ->
                document.toObject(Address::class.java)
            }
            onSuccess(addresses)
        }
        .addOnFailureListener { e ->
            onError(e)
        }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisposeScreen(navContoller: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Select Address", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navContoller.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        AddressFormContent(paddingValues)
    }

}

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressFormContent(paddingValues: PaddingValues) {
    var selectedOptions by remember { mutableStateOf(setOf<String>()) }
    val availableOptions = listOf("Nylon", "Hard-Plastic", "Soft-Plastic", "PVC", "PET", "Acrylic")
    val quantity = listOf("Bulk Quantity", "Small Quantity")

    var name by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var addressName by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    val cityOptions = listOf("Adilabad","Ahmedabad","Bengaluru","Chennai","Coimbatore","Delhi","Hanamkonda","Hyderabad","Indore","Jaipur","Kadapa","Karimnagar","Khamman","Kolkata","Lucknow","Mumbai","Nagpur","Pune","Surat","Vijaywada","Vijaynagaram","Vishakapatnam")
    var expanded by remember { mutableStateOf(false) }
    var scheduleDate by remember { mutableStateOf("") }
    val context = LocalContext.current

    Image(
            painter = painterResource(id = R.drawable.background2), // Replace with your background image resource
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.7f) // You can adjust the alpha value for transparency
                .zIndex(-1f)
        )
    val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxWidth()
                //.verticalScroll(scrollState)
                .background(Color.Transparent)
        ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                        .background(colorResource(id = R.color.green)) // Set the background color to green
                        .clip(RoundedCornerShape(30.dp))
                ) {
                    Text(
                        text = "Add Address",
                        fontWeight = FontWeight.Bold,
                        //style = MaterialTheme.typography.titleLarge,
                        //color = Color.White, // Optional: Change text color to white for better contrast
                        modifier = Modifier.padding(8.dp) // Add padding inside the box for the text
                    )
                }

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text(text = "Name", fontWeight = FontWeight.Bold) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        focusedIndicatorColor = Color.Blue,
                        unfocusedIndicatorColor = Color.Gray,
                        cursorColor = Color.Black,
                        focusedLabelColor = Color.Black,
                        unfocusedLabelColor = Color.Black,
                        containerColor = colorResource(id = R.color.cream)
                    ),
                    shape = RoundedCornerShape(16.dp)
                )
                OutlinedTextField(
                    value = phoneNumber,
                    onValueChange = { phoneNumber = it },
                    label = { Text(text = "Phone Number", fontWeight = FontWeight.Bold) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        focusedIndicatorColor = Color.Blue,
                        unfocusedIndicatorColor = Color.Gray,
                        cursorColor = Color.Black,
                        focusedLabelColor = Color.Black,
                        unfocusedLabelColor = Color.Black,
                        containerColor = colorResource(id = R.color.cream)
                    ),
                    shape = RoundedCornerShape(16.dp)
                )
                OutlinedTextField(
                    value = addressName,
                    onValueChange = { addressName = it },
                    label = {
                        Text(
                            text = "Address Name (Home, Work)",
                            fontWeight = FontWeight.Bold
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        focusedIndicatorColor = Color.Blue,
                        unfocusedIndicatorColor = Color.Gray,
                        cursorColor = Color.Black,
                        focusedLabelColor = Color.Black,
                        unfocusedLabelColor = Color.Black,
                        containerColor = colorResource(id = R.color.cream)
                    ),
                    shape = RoundedCornerShape(16.dp)
                )
//                OutlinedTextField(
//                    value = city,
//                    onValueChange = { city = it },
//                    label = { Text(text = "City", fontWeight = FontWeight.Bold) },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(bottom = 16.dp),
//                    colors = TextFieldDefaults.textFieldColors(
//                        focusedTextColor = Color.Black,
//                        unfocusedTextColor = Color.Black,
//                        focusedIndicatorColor = Color.Blue,
//                        unfocusedIndicatorColor = Color.Gray,
//                        cursorColor = Color.Black,
//                        focusedLabelColor = Color.Black,
//                        unfocusedLabelColor = Color.Black,
//                        containerColor = colorResource(id = R.color.cream)
//                    ),
//                    shape = RoundedCornerShape(16.dp)
//                )

            Box(modifier = Modifier
                //.fillMaxWidth()
                .padding(bottom = 16.dp)
                .clickable { expanded = true }
            ) {
                OutlinedTextField(
                    value = city,
                    onValueChange = { city = it },
                    label = { Text(text = "Select City", fontWeight = FontWeight.Bold) },
                    modifier = Modifier
                        .fillMaxWidth(),
                        //.clickable { expanded = true },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        focusedIndicatorColor = Color.Blue,
                        unfocusedIndicatorColor = Color.Gray,
                        cursorColor = Color.Black,
                        focusedLabelColor = Color.Black,
                        unfocusedLabelColor = Color.Black,
                        containerColor = colorResource(id = R.color.cream)
                    ),
                    shape = RoundedCornerShape(16.dp),
                    readOnly = true
                )

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    cityOptions.forEach { option ->
                        DropdownMenuItem(
                            onClick = {
                                city = option
                                expanded = false
                            }
                        ) {
                            Text(text = option, color = Color.Black)
                        }
                    }
                }
            }


            ScheduleDatePicker(context, scheduleDate) { selectedDate ->
                    scheduleDate = selectedDate
                }
//        Text(
//            text = "Select the type of plastic",
//            fontWeight = FontWeight.Bold,
//            style = MaterialTheme.typography.titleLarge,
//            modifier = Modifier.padding(bottom = 16.dp),
//        )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                        .background(
                            colorResource(id = R.color.green)
                        ),

                    )

                {
                    Text(
                        text = "Select Type of Plastic",
                        fontWeight = FontWeight.Bold,
                        //style = MaterialTheme.typography.titleLarge,
                        //color = Color.White, // Optional: Change text color to white for better contrast
                        modifier = Modifier.padding(8.dp) // Add padding inside the box for the text
                    )
                }


                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.fillMaxWidth(0.8f), // Adjust width to center
                        contentPadding = PaddingValues(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(availableOptions.size) { index ->
                            val option = availableOptions[index]
                            SelectableChip(
                                label = option,
                                isSelected = selectedOptions.contains(option),
                                onClick = {
                                    selectedOptions = if (selectedOptions.contains(option)) {
                                        selectedOptions - option
                                    } else {
                                        selectedOptions + option
                                    }
                                }
                            )
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                        .background(colorResource(id = R.color.green)),

                    ) {
                    Text(
                        text = "Select Quantity of Plastic",
                        fontWeight = FontWeight.Bold,
                        //style = MaterialTheme.typography.titleLarge,
                        //color = Color.White, // Optional: Change text color to white for better contrast
                        modifier = Modifier.padding(8.dp) // Add padding inside the box for the text
                    )
                }

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.fillMaxWidth(0.8f), // Adjust width to center
                        contentPadding = PaddingValues(8.dp),
                        horizontalArrangement = Arrangement.Center, //Arrangement.spacedBy(10.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        items(quantity.size) { index ->
                            val option = quantity[index]
                            SelectableChip(
                                label = option,
                                isSelected = selectedOptions.contains(option),
                                onClick = {
                                    selectedOptions = if (selectedOptions.contains(option)) {
                                        selectedOptions - option
                                    } else {
                                        selectedOptions + option
                                    }
                                }
                            )
                        }
                    }
                }

//        Button(
//            onClick = { /* Handle save address */ },
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(60.dp)
//                .padding(8.dp),
//            colors = ButtonDefaults.buttonColors(
//                containerColor = colorResource(id = R.color.green), // Change to your color
//                contentColor = Color.Black
//            )
//        ) {
//            Text(text = "SAVE ADDRESS", fontWeight = FontWeight.Bold, color = Color.White)
//        }
                Button(
                    onClick = {
                        val address = Address(
                            name = name,
                            phoneNumber = phoneNumber,
                            addressName = addressName,
                            city = city,
                            selectedPlastics = selectedOptions.toList(),
                            quantity = selectedOptions.toList(),
                            scheduleDate = scheduleDate
                        )
                        saveAddress(address,
                            onSuccess = { "Your Address Saved"/* Handle success, e.g., show a message */ },
                            onError = { e -> "Try Again"/* Handle error, e.g., show an error message */ }
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.green),
                        contentColor = Color.Black
                    )
                ) {
                    Text(text = "SAVE ADDRESS", fontWeight = FontWeight.Bold, color = Color.White)
                }

            }
        }



@Composable
fun ScheduleDatePicker(@SuppressLint("RestrictedApi") context: android.content.Context, selectedDate: String, onDateSelected: (String) -> Unit) {
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .background(colorResource(id = R.color.green))
            .clickable {
                DatePickerDialog(context, { _, year, month, dayOfMonth ->
                    val date = "$dayOfMonth/${month + 1}/$year"
                    onDateSelected(date)
                }, year, month, day).show()
            },
        contentAlignment = Alignment.Center
    )
    {
        Text(
            text = if (selectedDate.isEmpty()) "Select Schedule Date" else "Scheduled Date: $selectedDate",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun SelectableChip(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = if (isSelected) Color.Blue else colorResource(id = R.color.cream)
    val contentColor = if (isSelected) Color.White else Color.Black

    Surface(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .clickable(onClick = onClick),
        color = backgroundColor,
        shape = RoundedCornerShape(16.dp),
        tonalElevation = 4.dp // Adds shadow effect similar to elevation
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 0.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally, //Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = label,
                color = contentColor,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddressScreenPreview() {
    AddressFormContent(PaddingValues(0.dp))
}

@Composable
fun AddressListScreen() {
    var addresses by remember { mutableStateOf<List<Address>>(emptyList()) }

    LaunchedEffect(Unit) {
        getAddresses(
            onSuccess = { retrievedAddresses ->
                addresses = retrievedAddresses
            },
            onError = { e -> /* Handle error, e.g., show an error message */ }
        )
    }

    LazyColumn {
        this.items(addresses) { address ->
            Text(text = "Name: ${address.name}, City: ${address.city}")
        }
    }
}

