package com.example.plastic_radar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.example.plastic_radar.ui.theme.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString




fun signIn(
    auth: FirebaseAuth,
    email: String,
    password: String,
    onSignedIn: (FirebaseUser) -> Unit,
    onSignInError: (String) -> Unit
) {
    auth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = auth.currentUser
                if (user != null) {
                    onSignedIn(user)
                } else {
                    onSignInError("Sign In failed")
                }
            } else {
                onSignInError(task.exception?.message ?: "Sign In failed")
            }
        }
}

fun signUp(
    auth: FirebaseAuth,
    email: String,
    password: String,
    firstName: String,
    lastName: String,
    onSignedUp: (FirebaseUser) -> Unit,
    onSignUpError: (String) -> Unit
) {
    auth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = auth.currentUser
                if (user != null) {
                    onSignedUp(user)
                }
            }
                else{
                    val errorMessage = task.exception?.message ?: "Sign Up failed"
                    onSignUpError(errorMessage)
                }
        }
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthScreen(onSignedIn: (FirebaseUser) -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var isSignIn by remember { mutableStateOf(true) }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var myErrorMessage by remember { mutableStateOf<String?>(null) }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Background image
        Image(
            painter = painterResource(id = R.drawable.background2), // Replace with your background image resource
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.7f) // You can adjust the alpha value for transparency
            //.size(300.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (!isSignIn) {
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = firstName,
                    onValueChange = { firstName = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    label = { Text(text="First Name",
                        fontWeight = FontWeight.Bold ) },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        focusedIndicatorColor = Color.Blue,
                        unfocusedIndicatorColor = Color.Gray,
                        cursorColor = Color.Black,
                        focusedLabelColor = Color.Black,
                        unfocusedLabelColor = Color.Black,
                        containerColor = colorResource(id =R.color.cream)
                    ),
                    shape = RoundedCornerShape(16.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = lastName,
                    onValueChange = { lastName = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    label = { Text(text="Last Name",
                        fontWeight = FontWeight.Bold     ) },

                    colors = TextFieldDefaults.textFieldColors(
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        focusedIndicatorColor = Color.Blue,
                        unfocusedIndicatorColor = Color.Gray,
                        cursorColor = Color.Black,
                        focusedLabelColor = Color.Black,
                        unfocusedLabelColor = Color.Black,
                        containerColor = colorResource(id =R.color.cream)
                    ),
                    shape = RoundedCornerShape(16.dp) )
            }

            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text(text="Email",
                    fontWeight = FontWeight.Bold) },
                leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                colors = TextFieldDefaults.textFieldColors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedIndicatorColor = Color.Blue,
                    unfocusedIndicatorColor = Color.Gray,
                    cursorColor = Color.Black,
                    focusedLabelColor = Color.Black,
                    unfocusedLabelColor = Color.Black,
                    containerColor = colorResource(id =R.color.cream)
                ),
                shape = RoundedCornerShape(16.dp),
            )

            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text(text="Password",
                    fontWeight = FontWeight.Bold) },
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                        val icon =
                            if (isPasswordVisible) Icons.Default.Lock else Icons.Default.Search
                        Icon(imageVector = icon, contentDescription = "Toggle Password Visibility")
                    }
                },
                colors = TextFieldDefaults.textFieldColors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedIndicatorColor = Color.Blue,
                    unfocusedIndicatorColor = Color.Gray,
                    cursorColor = Color.Black,
                    focusedLabelColor = Color.Black,
                    unfocusedLabelColor = Color.Black,
                    containerColor = colorResource(id =R.color.cream)
                ),
                shape = RoundedCornerShape(16.dp),
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (myErrorMessage != null) {
                Text(
                    text = myErrorMessage!!,
                    color = Color.Red,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    if (isSignIn) {
                        signIn(
                            Firebase.auth, email, password,
                            onSignedIn = { signedInUser -> onSignedIn(signedInUser) },
                            onSignInError = { errorMessage -> myErrorMessage = errorMessage },
                        )
                    } else {
                        signUp(
                            Firebase.auth,
                            email,
                            password,
                            firstName,
                            lastName,
                            onSignedUp = { signedInUser -> onSignedIn(signedInUser) },
                            onSignUpError = { errorMessage -> myErrorMessage = errorMessage }
                            )
//                        { signedInUser ->
//                            onSignedIn(signedInUser)
//                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id =R.color.green), // Change to your color
                    contentColor = Color.White)
            ) {
                Text(text = if (isSignIn) "Sign In" else "Sign Up", fontSize = 18.sp)
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(8.dp)
            ) {
//                ClickableText(
//
//                    text = AnnotatedString(if (isSignIn) "Create an account" else "Already have an account? Sign In"),
//                    onClick = { isSignIn = !isSignIn },
//                    modifier = Modifier
//                        .align(Alignment.Center)
//                )
                ClickableText(
                    text = buildAnnotatedString {
                        append(
                            if (isSignIn) "Create an account"
                            else "Already have an account? Sign In"
                        )
                        addStyle(style = SpanStyle(fontWeight = FontWeight.Bold), start = 0, end = length)
                    },
                    onClick = { isSignIn = !isSignIn },
                    modifier = Modifier.align(Alignment.Center)
                )

            }

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
