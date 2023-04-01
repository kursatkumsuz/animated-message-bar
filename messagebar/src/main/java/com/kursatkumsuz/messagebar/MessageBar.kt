package com.kursatkumsuz.messagebar

import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import java.util.*

@Composable
fun rememberAnimatedBarState(): AnimatedMessageBar {
    return remember { AnimatedMessageBar() }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MessageBarContent(
    barState: AnimatedMessageBar,
    duration: Long = 1500L,
    messageBarContent : @Composable () -> Unit
) {
    var showBarState by remember { mutableStateOf(false) }
    val message = barState.barMessage
    val type = barState.barType
    val showState = barState.showState

    val backgroundColor = when (type) {
        BarType.SUCCESS -> Color(0xFF40A3EE)
        else -> Color(0xFFF5217D)
    }

    val barIcon = when (type) {
        BarType.SUCCESS -> R.drawable.ic_success
        else -> R.drawable.ic_error
    }

    val enterAnimation: EnterTransition = fadeIn() + scaleIn(
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    val exitAnimation: ExitTransition = fadeOut() + scaleOut()

    LaunchedEffect(key1 = showState) {
        showBarState = true
        delay(duration)
        showBarState = false
    }

    messageBarContent()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        AnimatedVisibility(
            visible = showBarState && message.isNotEmpty(),
            enter = enterAnimation,
            exit = exitAnimation
        ) {
            MessageBar(
                message = message,
                icon = barIcon,
                backgroundColor = backgroundColor,
            )
        }
    }
}


@Composable
internal fun MessageBar(
    message: String?,
    icon: Int,
    backgroundColor: Color,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = backgroundColor)
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(Modifier.width(5.dp))
        Icon(
            painter = painterResource(icon),
            contentDescription = "Message Bar Icon",
            tint = Color.White
        )
        Spacer(Modifier.width(5.dp))
        Text(text = message ?: "", color = Color.White, fontSize = 15.sp)
        Spacer(Modifier.width(5.dp))
    }
}