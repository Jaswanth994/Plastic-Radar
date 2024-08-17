package com.example.plastic_radar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.userProfileChangeRequest
import com.google.firebase.ktx.Firebase

@Composable
fun AuthScreen(onSignedIn: (FirebaseUser) -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var isSignIn by remember { mutableStateOf(true) }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var myErrorMessage by remember { mutableStateOf<String?>(null) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_1),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize().alpha(0.7f)
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
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    label = { Text("First Name") }
                )

                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = lastName,
                    onValueChange = { lastName = it },
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    label = { Text("Last Name") }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                label = { Text("Email") },
                leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email)
            )

            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                label = { Text("Password") },
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                        val icon =
                            if (isPasswordVisible) Icons.Default.Lock else Icons.Default.Search
                        Icon(imageVector = icon, contentDescription = "Toggle Password Visibility")
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (myErrorMessage != null) {
                Text(
                    text = myErrorMessage!!,
                    color = Color.Red,
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    if (isSignIn) {
                        signIn(
                            auth = Firebase.auth,
                            email = email,
                            password = password,
                            onSignedIn = { signedInUser -> onSignedIn(signedInUser) },
                            onSignInError = { errorMessage -> myErrorMessage = errorMessage }
                        )

                    } else {
                        signUp(
                            Firebase.auth,
                            email,
                            password,
                            firstName,
                            lastName,
                            onSignUpError = { errorMessage -> myErrorMessage = errorMessage }
                        ) { signedInUser ->
                            onSignedIn(signedInUser)
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth().height(60.dp).padding(8.dp),
            ) {
                Text(text = if (isSignIn) "Sign In" else "Sign Up", fontSize = 18.sp)
            }

            Box(
                modifier = Modifier.fillMaxWidth().height(50.dp).padding(8.dp)
            ) {
                ClickableText(
                    text = AnnotatedString(if (isSignIn) "Create an account" else "Already have an account? Sign In"),
                    onClick = { isSignIn = !isSignIn },
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

fun signIn(
    auth: FirebaseAuth,
    email: String,
    password: String,
    onSignedIn: (FirebaseUser) -> Unit,
    onSignInError: (String) -> Unit
) {
    if (email.isBlank() || password.isBlank()) {
        onSignInError("Email and password must not be empty")
        return
    }

    auth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = auth.currentUser
                if (user != null) {
                    onSignedIn(user)
                } else {
                    onSignInError("User authentication failed")
                }
            } else {
                onSignInError("Sign in failed: ${task.exception?.localizedMessage}")
            }
        }
}

fun signUp(
    auth: FirebaseAuth,
    email: String,
    password: String,
    firstName: String,
    lastName: String,
    onSignUpError: (String) -> Unit,
    onSignedUp: (FirebaseUser) -> Unit
) {
    if (email.isBlank() || password.isBlank() || firstName.isBlank() || lastName.isBlank()) {
        onSignUpError("All fields must be filled out")
        return
    }

    auth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = auth.currentUser
                if (user != null) {
                    // Optionally, you can update the user's profile here
                    val profileUpdates = userProfileChangeRequest {
                        var displayName = "$firstName $lastName"
                    }
                    user.updateProfile(profileUpdates)
                        .addOnCompleteListener {
                            onSignedUp(user)
                        }
                } else {
                    onSignUpError("User registration failed")
                }
            } else {
                onSignUpError("Sign up failed: ${task.exception?.localizedMessage}")
            }
        }
}
