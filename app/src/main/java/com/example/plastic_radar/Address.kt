package com.example.plastic_radar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


//class AddressScreen : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            AddressScreenContent()
//        }
//    }
//}
//
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun AddressScreenContent() {
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text(text="Select Address",
//                    fontWeight = FontWeight.Bold) },
//                navigationIcon = {
//                    IconButton(onClick = { /* Handle back press */ }) {
//                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
//                    }
//                }
//            )
//        }
//    ) { paddingValues ->
//        AddressFormContent(paddingValues)
//    }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun AddressFormContent(paddingValues: PaddingValues) {
//    Image(
//        painter = painterResource(id = R.drawable.background2), // Replace with your background image resource
//        contentDescription = null,
//        contentScale = ContentScale.Crop,
//        modifier = Modifier
//            .fillMaxSize()
//            .alpha(0.7f) // You can adjust the alpha value for transparency
//        //.size(300.dp)
//    )
//    Column(
//        modifier = Modifier
//            .padding(paddingValues)
//            .padding(16.dp)
//    ) {
//        Text(
//            text = "ADD NEW ADDRESS",
//            fontWeight = FontWeight.Bold,
//            style = MaterialTheme.typography.titleLarge,
//            modifier = Modifier.padding(bottom = 16.dp),
//        )
//
//        OutlinedTextField(
//            value = "",
//            onValueChange = { /* Handle text change */ },
//            label = { Text(text="Name",
//                fontWeight = FontWeight.Bold) },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(bottom = 16.dp),
//            colors = TextFieldDefaults.textFieldColors(
//                focusedTextColor = Color.Black,
//                unfocusedTextColor = Color.Black,
//                focusedIndicatorColor = Color.Blue,
//                unfocusedIndicatorColor = Color.Gray,
//                cursorColor = Color.White,
//                focusedLabelColor = Color.Black,
//                unfocusedLabelColor = Color.Black,
//                containerColor = colorResource(id =R.color.cream)
//            ),
//            shape = RoundedCornerShape(16.dp)
//        )
//        OutlinedTextField(
//            value = "",
//            onValueChange = { /* Handle text change */ },
//            label = { Text(text="Phone Number",
//                fontWeight = FontWeight.Bold) },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(bottom = 16.dp),
//            colors = TextFieldDefaults.textFieldColors(
//                focusedTextColor = Color.Black,
//                unfocusedTextColor = Color.Black,
//                focusedIndicatorColor = Color.Blue,
//                unfocusedIndicatorColor = Color.Gray,
//                cursorColor = Color.White,
//                focusedLabelColor = Color.Black,
//                unfocusedLabelColor = Color.Black,
//                containerColor = colorResource(id =R.color.cream)
//            ),
//            shape = RoundedCornerShape(16.dp)
//        )
//        OutlinedTextField(
//            value = "",
//            onValueChange = { /* Handle text change */ },
//            label = { Text(text="Address Name (Home, Work)",
//                fontWeight = FontWeight.Bold) },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(bottom = 16.dp),
//            colors = TextFieldDefaults.textFieldColors(
//                focusedTextColor = Color.Black,
//                unfocusedTextColor = Color.Black,
//                focusedIndicatorColor = Color.Blue,
//                unfocusedIndicatorColor = Color.Gray,
//                cursorColor = Color.White,
//                focusedLabelColor = Color.Black,
//                unfocusedLabelColor = Color.Black,
//                containerColor = colorResource(id =R.color.cream)
//            ),
//            shape = RoundedCornerShape(16.dp)
//        )
//
//        OutlinedTextField(
//            value = "",
//            onValueChange = { /* Handle text change */ },
//            label = { Text(text="Address Line 1",
//                fontWeight = FontWeight.Bold) },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(bottom = 16.dp),
//            colors = TextFieldDefaults.textFieldColors(
//                focusedTextColor = Color.Black,
//                unfocusedTextColor = Color.Black,
//                focusedIndicatorColor = Color.Blue,
//                unfocusedIndicatorColor = Color.Gray,
//                cursorColor = Color.White,
//                focusedLabelColor = Color.Black,
//                unfocusedLabelColor = Color.Black,
//                containerColor = colorResource(id =R.color.cream)
//            ),
//            shape = RoundedCornerShape(16.dp)
//        )
//
////        OutlinedTextField(
////            value = "",
////            onValueChange = { /* Handle text change */ },
////            label = { Text("Pick your location") },
////            modifier = Modifier
////                .fillMaxWidth()
////                .padding(bottom = 24.dp),
////            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
////        )
//
//        Button(
//            onClick = { /* Handle save address */},
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(60.dp)
//                .padding(8.dp),
//            colors = ButtonDefaults.buttonColors(
//                containerColor = colorResource(id =R.color.green), // Change to your color
//                contentColor = Color.White)
//
//        ) {
//            Text(text="SAVE ADDRESS",
//                fontWeight = FontWeight.Bold,)
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun AddressScreenPreview() {
//    AddressScreenContent()
//}


class AddressScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AddressScreenContent()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressScreenContent() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Select Address", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back press */ }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }

            )
        }
    ) { paddingValues ->
        AddressFormContent(paddingValues)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressFormContent(paddingValues: PaddingValues) {
    var selectedOptions by remember { mutableStateOf(setOf<String>()) }
    val availableOptions = listOf("Nylon", "HeavyPlastic", "Water", "Air","Heat")
    val quantity = listOf("Bulk Quantity","Small Quantity")

    Image(
        painter = painterResource(id = R.drawable.background2), // Replace with your background image resource
        contentDescription = null,
        contentScale = ContentScale.Crop,

        modifier = Modifier
            .fillMaxSize()
            .alpha(0.7f) // You can adjust the alpha value for transparency
    )
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(16.dp)
    ) {
        Text(
            text = "ADD NEW ADDRESS",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp),
        )

        OutlinedTextField(
            value = "",
            onValueChange = { /* Handle text change */ },
            label = { Text(text = "Name", fontWeight = FontWeight.Bold) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedIndicatorColor = Color.Blue,
                unfocusedIndicatorColor = Color.Gray,
                cursorColor = Color.White,
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.Black,
                containerColor = colorResource(id = R.color.cream)
            ),
            shape = RoundedCornerShape(16.dp)
        )
        OutlinedTextField(
            value = "",
            onValueChange = { /* Handle text change */ },
            label = { Text(text = "Phone Number", fontWeight = FontWeight.Bold) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedIndicatorColor = Color.Blue,
                unfocusedIndicatorColor = Color.Gray,
                cursorColor = Color.White,
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.Black,
                containerColor = colorResource(id = R.color.cream)
            ),
            shape = RoundedCornerShape(16.dp)
        )
        OutlinedTextField(
            value = "",
            onValueChange = { /* Handle text change */ },
            label = { Text(text = "Address Name (Home, Work)", fontWeight = FontWeight.Bold) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedIndicatorColor = Color.Blue,
                unfocusedIndicatorColor = Color.Gray,
                cursorColor = Color.White,
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.Black,
                containerColor = colorResource(id = R.color.cream)
            ),
            shape = RoundedCornerShape(16.dp)
        )
        OutlinedTextField(
            value = "",
            onValueChange = { /* Handle text change */ },
            label = { Text(text = "City", fontWeight = FontWeight.Bold) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedIndicatorColor = Color.Blue,
                unfocusedIndicatorColor = Color.Gray,
                cursorColor = Color.White,
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.Black,
                containerColor = colorResource(id = R.color.cream)
            ),
            shape = RoundedCornerShape(16.dp)
        )
        Text(
            text = "Select the type of plastic",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp),
        )

//        LazyVerticalGrid(
//            columns = GridCells.Fixed(3),
//            modifier = Modifier
//                .padding(bottom = 16.dp)
//                .fillMaxWidth(),
//            contentPadding = PaddingValues(8.dp),
//            horizontalArrangement = Arrangement.spacedBy(8.dp),
//            verticalArrangement = Arrangement.spacedBy(8.dp)
//        ) {
//            items(availableOptions.size) { index ->
//                val option = availableOptions[index]
//                SelectableChip(
//                    label = option,
//                    isSelected = selectedOptions.contains(option),
//                    onClick = {
//                        selectedOptions = if (selectedOptions.contains(option)) {
//                            selectedOptions - option
//                        } else {
//                            selectedOptions + option
//                        }
//                    }
//                )
//            }
//        }
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
        Text(
            text = "Select quantity of Plastic",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp),
        )
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxWidth(0.8f), // Adjust width to center
                contentPadding = PaddingValues(8.dp),
                horizontalArrangement = Arrangement.Center,//Arrangement.spacedBy(10.dp),
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
            onClick = { /* Handle save address */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.green), // Change to your color
                contentColor = Color.White
            )
        ) {
            Text(text = "SAVE ADDRESS", fontWeight = FontWeight.Bold)
        }
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
        Box(
            modifier = Modifier
                .padding(horizontal = 0.dp, vertical = 8.dp),


        ) {
            Text(
                text = label,
                color = Color.Black,
                fontWeight =  FontWeight.Bold,
                textAlign= TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun AddressScreenPreview() {
    AddressScreenContent()
}



