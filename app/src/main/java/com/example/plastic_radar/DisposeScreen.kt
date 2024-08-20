package com.example.plastic_radar
import android.annotation.SuppressLint
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.plastic_radar.firbase.saveOrderToFirestore
import com.example.plastic_radar.firbase.Order
import com.google.firebase.auth.FirebaseAuth
import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.plastic_radar.ui.theme.gr
import com.example.plastic_radar.ui.theme.tealll
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Calendar






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
        AddressFormContent(navContoller,paddingValues)
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressFormContent(navContoller: NavController,paddingValues: PaddingValues) {
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
    var nameError by remember { mutableStateOf("") }
    var phoneNumberError by remember { mutableStateOf("") }
    var addressNameError by remember { mutableStateOf("") }
    var cityError by remember { mutableStateOf("") }
    var scheduleDateError by remember { mutableStateOf("") }
    var plasticError by remember { mutableStateOf("") }
    var quantityError by remember { mutableStateOf("") }

    val firestore = FirebaseFirestore.getInstance()
    val userId = FirebaseAuth.getInstance().currentUser?.uid

    fun validateInputs(
        name: String,
        phoneNumber: String,
        addressName: String,
        city: String,
        scheduleDate: String,
        selectedOptions: Set<String>,
        quantity: List<String>
    ): Boolean {
        var isValid = true

        // Reset error messages
        nameError = ""
        phoneNumberError = ""
        addressNameError = ""
        cityError = ""
        scheduleDateError = ""
        plasticError = ""
        quantityError = ""

        if (name.isEmpty()) {
            nameError = "Name cannot be empty"
            isValid = false
        }

        if (phoneNumber.length != 10) {
            phoneNumberError = "Phone number must be 10 digits"
            isValid = false
        }

        if (addressName.isEmpty()) {
            addressNameError = "Address name cannot be empty"
            isValid = false
        }

        if (city.isEmpty()) {
            cityError = "City must be selected"
            isValid = false
        }

        if (scheduleDate.isEmpty()) {
            scheduleDateError = "Schedule date cannot be empty"
            isValid = false
        } else {
            // Check if schedule date is not in the past
            val currentDate = Calendar.getInstance()
            val selectedDateParts = scheduleDate.split("/").map { it.toInt() }
            val selectedCalendar = Calendar.getInstance().apply {
                set(selectedDateParts[2], selectedDateParts[1] - 1, selectedDateParts[0])
            }

            if (selectedCalendar.before(currentDate)) {
                scheduleDateError = "Schedule date cannot be in the past"
                isValid = false
            }
        }

        if (selectedOptions.isEmpty()) {
            plasticError = "At least one plastic type must be selected"
            isValid = false
        }

        if (quantity.size != 1) {
            quantityError = "Select exactly one quantity"
            isValid = false
        }

        return isValid
    }

//    Image(
//        painter = painterResource(id = R.drawable.background2), // Replace with your background image resource
//        contentDescription = null,
//        contentScale = ContentScale.Crop,
//        modifier = Modifier
//            .fillMaxSize()
//            .alpha(0.7f) // You can adjust the alpha value for transparency
//            .zIndex(-1f)
//    )
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(16.dp)
            .background(Color.Transparent)
    ) {
        // Error message for Name
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(text = "Name", fontWeight = FontWeight.Bold) },
            isError = nameError.isNotEmpty(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 2.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedIndicatorColor = Color.Blue,
                unfocusedIndicatorColor = Color.Gray,
                cursorColor = Color.Black,
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.Black,
                containerColor = tealll
            ),
            shape = RoundedCornerShape(16.dp)
        )
        if (nameError.isNotEmpty()) {
            Text(text = nameError, color = Color.Red, style = TextStyle(fontSize = 12.sp))
        }

        // Error message for Phone Number
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text(text = "Phone Number", fontWeight = FontWeight.Bold) },
            isError = phoneNumberError.isNotEmpty(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 2.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = TextFieldDefaults.textFieldColors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedIndicatorColor = Color.Blue,
                unfocusedIndicatorColor = Color.Gray,
                cursorColor = Color.Black,
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.Black,
                containerColor = tealll
            ),
            shape = RoundedCornerShape(16.dp)
        )
        if (phoneNumberError.isNotEmpty()) {
            Text(text = phoneNumberError, color = Color.Red, style = TextStyle(fontSize = 12.sp))
        }

        // Error message for Address Name
        OutlinedTextField(
            value = addressName,
            onValueChange = { addressName = it },
            label = { Text(text = "Address Name (Home, Work)", fontWeight = FontWeight.Bold) },
            isError = addressNameError.isNotEmpty(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 2.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedIndicatorColor = Color.Blue,
                unfocusedIndicatorColor = Color.Gray,
                cursorColor = Color.Black,
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.Black,
                containerColor = tealll
            ),
            shape = RoundedCornerShape(16.dp)
        )
        if (addressNameError.isNotEmpty()) {
            Text(text = addressNameError, color = Color.Red, style = TextStyle(fontSize = 12.sp))
        }

        // Error message for City
        Box(modifier = Modifier
            .padding(bottom = 2.dp)
            .clickable { expanded = true }
        ) {
            OutlinedTextField(
                value = city,
                onValueChange = { city = it },
                label = { Text(text = "Select City", fontWeight = FontWeight.Bold) },
                isError = cityError.isNotEmpty(),
                modifier = Modifier
                    .fillMaxWidth(),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = "Drop down arrow",
                        Modifier.clickable { expanded = !expanded }
                    )
                },
                readOnly = true,
                colors = TextFieldDefaults.textFieldColors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedIndicatorColor = Color.Blue,
                    unfocusedIndicatorColor = Color.Gray,
                    cursorColor = Color.Black,
                    focusedLabelColor = Color.Black,
                    unfocusedLabelColor = Color.Black,
                    containerColor = tealll
                ),
                shape = RoundedCornerShape(16.dp)
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                properties = PopupProperties(focusable = true),
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
        if (cityError.isNotEmpty()) {
            Text(text = cityError, color = Color.Red, style = TextStyle(fontSize = 12.sp))
        }

        // Error message for Schedule Date
        ScheduleDatePicker(context, scheduleDate) { selectedDate ->
            scheduleDate = selectedDate
        }
        if (scheduleDateError.isNotEmpty()) {
            Text(text = scheduleDateError, color = Color.Red, style = TextStyle(fontSize = 12.sp))
        }

        // Plastic Type Selection Error Message
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 2.dp)
                .background(gr),
        ) {
            Text(
                text = "Select Type of Plastic",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(8.dp)
            )
        }
        if (plasticError.isNotEmpty()) {
            Text(text = plasticError, color = Color.Red, style = TextStyle(fontSize = 12.sp))
        }
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxWidth(0.8f),
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

        // Quantity Selection Error Message
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 2.dp)
                .background(gr),
        ) {
            Text(
                text = "Select Quantity of Plastic",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(8.dp)
            )
        }
        if (quantityError.isNotEmpty()) {
            Text(text = quantityError, color = Color.Red, style = TextStyle(fontSize = 12.sp))
        }
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxWidth(0.8f),
                contentPadding = PaddingValues(8.dp),
                horizontalArrangement = Arrangement.Center,
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

        Button(
            onClick = {
                val isValid = validateInputs(
                    name = name,
                    phoneNumber = phoneNumber,
                    addressName = addressName,
                    city = city,
                    scheduleDate = scheduleDate,
                    selectedOptions = selectedOptions,
                    quantity = quantity.filter { selectedOptions.contains(it) }
                )

                if (isValid && userId != null) {
                    val order = Order(
                        name = name,
                        phoneNumber = phoneNumber,
                        addressName = addressName,
                        city = city,
                        scheduleDate = scheduleDate,
                        selectedOptions = selectedOptions.toList(),
                        quantity = quantity.filter { selectedOptions.contains(it) }
                    )

                    saveOrderToFirestore(userId, order, firestore)
                }
                navContoller.navigate(Routes.ScrapOrderHistoryScreen)
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
            .padding(bottom = 8.dp)
            .background(gr)
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
    val backgroundColor = if (isSelected) Color.Blue else tealll
    val contentColor = if (isSelected) Color.White else Color.Black

    Surface(
        modifier = Modifier
            .padding(horizontal = 8.dp)
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




//@Preview(showBackground = true)
//@Composable
//fun DisposeScreenPreview() {
//    val navController = rememberNavController() // Create a mock NavController
//    DisposeScreen(navContoller = navController)
//}