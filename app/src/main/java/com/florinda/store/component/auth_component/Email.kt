package com.florinda.store.component.auth_component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.florinda.store.ui.theme.colorBackgroundDark
import com.florinda.store.ui.theme.colorItemsDark
import com.florinda.store.ui.theme.colorItemsLight
import com.florinda.store.R

@Composable
fun Email(
    darkTheme: Boolean,
    showError: Boolean = false,
    onEmailChanged: (String) -> Unit
) {
    var useremail by remember { mutableStateOf("") }
    LaunchedEffect(key1 = useremail) {
        onEmailChanged(useremail)
    }
    Column {


        TextField(
            value = useremail,
            leadingIcon = {
                Row(
                    modifier = Modifier.wrapContentWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    content = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = "",
                            tint = if (darkTheme) Color.White else Color.Gray,
                            modifier = Modifier.padding(start = 10.dp)
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
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = if (darkTheme) colorBackgroundDark else Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                cursorColor = if (darkTheme) Color.White else Color.Gray,
                textColor = if (darkTheme) Color.White else Color.Gray,
            ),
            modifier = Modifier.fillMaxWidth()
                .padding(start = 10.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            label = {
                Text(
                    text = stringResource(R.string.email),
                    color = if (darkTheme) colorItemsLight else colorItemsDark,
                    modifier = Modifier.padding(start = 10.dp)
                )
            },
            shape = RoundedCornerShape(24.dp),
            onValueChange = {
                useremail = it
            },
            isError = showError,
        )

        if (showError) {
            Text(
                text = "Forget Email",
                color = Color.Red,
                modifier = Modifier.padding(start = 16.dp),
                fontSize = 12.sp
            )
        }

    }
}