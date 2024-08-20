package com.example.plastic_radar.Profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.plastic_radar.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PoliciesScreen(navController: NavController) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
        // Top App Bar with back arrow and "Policies" text
        TopAppBar(
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = {  navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Policies",
                        fontSize = 20.sp, color = Color.Black
                    )
                }
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = colorResource(id = R.color.white),
                titleContentColor = Color.Black
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        PolicyHeader("Privacy Policy")
        PolicyContent("Your privacy is our priority. We ensure that your personal information is collected, used, and stored responsibly. We do not share or sell your data to third parties without your consent. Our app collects only the necessary information to provide you with the best experience, including location data, preferences, and app usage statistics.")

        Spacer(modifier = Modifier.height(16.dp))

        PolicyHeader("Data Security")
        PolicyContent("We take data security seriously. All sensitive data is encrypted, and we implement the latest security measures to protect your information from unauthorized access, misuse, or disclosure.")

        Spacer(modifier = Modifier.height(16.dp))

        PolicyHeader("Terms of Use")
        PolicyContent("By using Plastic Radar, you agree to comply with our terms of use. Our app is designed to provide you with accurate, reliable information and services related to plastic products and sustainability efforts. Misuse of the app, including unauthorized distribution, modification, or any harmful activity, is strictly prohibited.")

        Spacer(modifier = Modifier.height(16.dp))

        PolicyHeader("Cookie Policy")
        PolicyContent("We use cookies to improve app performance and enhance your experience. Cookies help us understand how users interact with the app, allowing us to make improvements. You can manage cookie preferences in your device settings.")

        Spacer(modifier = Modifier.height(16.dp))

        PolicyHeader("User Responsibilities")
        PolicyContent("Users are responsible for the accuracy of the information provided during app registration or use. Any false or misleading information may result in restricted access or account suspension.")

        Spacer(modifier = Modifier.height(16.dp))

        PolicyHeader("Updates to Policies")
        PolicyContent("We may update our policies from time to time to ensure compliance with legal requirements and enhance user experience. You will be notified of any major changes via app notifications or email.")

        Spacer(modifier = Modifier.height(16.dp))

        PolicyFooter()
    }
}

@Composable
fun PolicyHeader(title: String) {
    Text(
        text = title,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary
    )
}

@Composable
fun PolicyContent(content: String) {
    Text(
        text = content,
        fontSize = 16.sp,
        color = MaterialTheme.colorScheme.onBackground
    )
}

@Composable
fun PolicyFooter() {
    Text(
        text = "If you have any questions about our policies, please contact us at support@plasticradar.com.",
        fontSize = 14.sp,
        fontWeight = FontWeight.Light,
        color = MaterialTheme.colorScheme.secondary
    )
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewPoliciesScreen() {
//    PoliciesScreen(navController = rememberNavController())
//}