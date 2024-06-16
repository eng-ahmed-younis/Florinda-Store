package com.florinda.store.app_component.auth_component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.florinda.store.R
import com.florinda.store.ui.theme.colorBackgroundDark
import com.florinda.store.ui.theme.colorItemsDark
import com.florinda.store.ui.theme.colorItemsLight
import com.florinda.store.ui.theme.colorRedWhite

@Composable
fun PhoneNumber(darkTheme: Boolean, onPhoneNumberChanged: (String) -> Unit) {

    var userPhoneNumber by remember { mutableStateOf("") }
    val maxChar = 15
    LaunchedEffect(key1 = userPhoneNumber){
        onPhoneNumberChanged(userPhoneNumber)
    }

    TextField(
        value = userPhoneNumber,
        onValueChange = {
            if (it.length <= maxChar) userPhoneNumber = it
        },
        singleLine = true,
        // the optional leading icon to be displayed at the beginning of the text field
        leadingIcon = {
            Row(
                modifier = Modifier.wrapContentWidth(),
                verticalAlignment = Alignment.CenterVertically,
                content = {
                    Image(
                        painter = painterResource(id = R.drawable.egypt_flat),
                        contentDescription = "egypt flat",
                        modifier = Modifier
                            .size(24.dp)
                            .padding(start = 10.dp)
                    )
                    Text(
                        text = "+20",
                        color = if (darkTheme) colorItemsLight else colorItemsDark,
                        modifier = Modifier
                            .padding(start = 10.dp)
                    )
                    Canvas(
                        modifier = Modifier
                            .height(24.dp)
                            .padding(start = 10.dp, end = 10.dp)
                    ) {
                        drawLine(
                            color = if (darkTheme) Color.White else Color.Gray,
                            start = Offset(0f, 0f),
                            end = Offset(0f, size.height),
                            strokeWidth = 2.0f
                        )
                    }
                }
            )
        },
        trailingIcon = {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(shape = CircleShape)
                    .background(if (darkTheme) Color.White else colorRedWhite)
                    .clickable {
                        userPhoneNumber = ""
                    }
            ) {
                Image(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(1.5.dp)

                )
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = if (darkTheme) colorBackgroundDark else Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            cursorColor = if (darkTheme) Color.White else Color.Gray,
            textColor = if (darkTheme) Color.White else Color.Gray,
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        label = {
            Text(
                text = stringResource(id = R.string.phone_number_label),
                color = if (isSystemInDarkTheme()) colorItemsLight else colorItemsDark,
                modifier = Modifier.padding(start = 10.dp)
            )
        },
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(24.dp)

    )
}