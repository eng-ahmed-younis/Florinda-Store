package com.florinda.store.app_component.auth_component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.florinda.store.ui.theme.colorWhite
@Composable
fun UserButton(
    darkTheme: Boolean,
    modifier: Modifier = Modifier,
    title :String,
    backgroundColor : Color,
    onclick: () -> Unit
) {
    Button(
        onClick = { onclick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor
        ),
        modifier = modifier
            .height(50.dp),
        shape = RoundedCornerShape(18.dp)
    ) {
        Text(
            text = title,
            color = colorWhite ,
            modifier = Modifier.padding(4.dp),
            style = TextStyle(
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            ),
        )
    }
}