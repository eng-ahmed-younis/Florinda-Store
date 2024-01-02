package com.florinda.store.component.auth_component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.florinda.store.ui.theme.colorWhite
@Composable
fun UserButton(
    darkTheme: Boolean,
    title :String,
    backgroundColor : Color,
    onclick: () -> Unit
) {
    Button(
        onClick = { onclick() },
        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        shape = RoundedCornerShape(18.dp)
    ) {
        Text(
            text = title,
            color = colorWhite ,
            style = MaterialTheme.typography.button,
            modifier = Modifier.padding(8.dp),
            fontSize = 16.sp
        )
    }
}