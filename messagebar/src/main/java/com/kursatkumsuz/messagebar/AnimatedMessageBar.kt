package com.kursatkumsuz.messagebar

import androidx.compose.runtime.*

class AnimatedMessageBar {
    var barType by mutableStateOf(BarType.SUCCESS)
    var barMessage by mutableStateOf("")
    var showState by mutableStateOf(false)


    fun AnimatedMessageBar(
        message: String,
        type: BarType
    ) {
        barMessage = message
        barType = type
        showState = !showState
    }
}