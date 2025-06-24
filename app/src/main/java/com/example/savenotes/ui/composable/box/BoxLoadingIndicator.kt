package com.example.savenotes.ui.composable.box

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.example.savenotes.ui.util.noRippleClickable

@Composable
fun BoxLoadingIndicator(isLoading: Boolean, customHeight: Dp? = null, isVisible: Boolean = true) {

    val modifier =
        if (customHeight == null) Modifier.fillMaxSize()
        else Modifier.fillMaxWidth().height(customHeight)

    if (isLoading) {
        Box(
            modifier = modifier.background(Color.Transparent).noRippleClickable {},
            contentAlignment = Alignment.Center,
        ) {
            if (isVisible) CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
        }
    }
}
