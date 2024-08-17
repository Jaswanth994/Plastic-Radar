
package com.example.plastic_radar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ChangeLanguageScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp))
    ) {
        Text(text = "Select a Language", fontSize = 20.sp, color = Color.Black)

        Spacer(modifier = Modifier.height(16.dp))

        // List of languages with icons
        val languages = listOf(
            Pair(Icons.Default.Language, "English"),
            Pair(Icons.Default.Translate, "Hindi"),
            Pair(Icons.Default.Translate, "Telugu"),
            Pair(Icons.Default.Translate, "Tamil"),
            Pair(Icons.Default.Translate, "Kannada")
        )

        languages.forEach { language ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(Color(0xFFE0FFE0), shape = RoundedCornerShape(8.dp))
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = language.first,
                    contentDescription = language.second,
                    tint = Color(0xFF008000),
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = language.second,
                    fontSize = 18.sp,
                    color = Color(0xFF008000)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewChangeLanguageScreen() {
    ChangeLanguageScreen()
}
