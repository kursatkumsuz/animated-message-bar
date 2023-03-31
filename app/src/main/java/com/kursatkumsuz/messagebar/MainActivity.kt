package com.kursatkumsuz.messagebar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.kursatkumsuz.messagebar.ui.theme.MessageBarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MessageBarTheme {
            }
        }
    }
}

