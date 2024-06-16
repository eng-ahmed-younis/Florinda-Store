package com.florinda.store.app_component.auth_component

import android.content.res.Configuration
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
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.florinda.store.R

import com.florinda.store.ui.theme.colorBackgroundDark
import com.florinda.store.ui.theme.colorItemsDark
import com.florinda.store.ui.theme.colorItemsLight


@Composable
fun Password(
    darkTheme: Boolean,
    showError: Boolean = false,
    onPasswordChanged: (String) -> Unit
) {
    var userPassword by rememberSaveable { mutableStateOf("") }
    var passwordVisibility by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(key1 = userPassword){
        onPasswordChanged(userPassword)
    }

    Column {


        TextField(
            value = userPassword,
            onValueChange = {
                userPassword = it
            },
            modifier = Modifier.fillMaxWidth()
                .padding(start = 10.dp),
            shape = RoundedCornerShape(24.dp),
            label = {
                Text(
                    text = stringResource(id = R.string.password_label),
                    color = if (darkTheme) colorItemsLight else colorItemsDark,
                    modifier = Modifier.padding(start = 10.dp)
                )
            },
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            leadingIcon = {
                Row(
                    modifier = Modifier
                        .wrapContentWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Lock,
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
            },
            trailingIcon = {
                val icon =
                    if (passwordVisibility) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                val description = if (passwordVisibility) "Hidden Password" else "Show Password"
                IconButton(
                    onClick = { passwordVisibility = !passwordVisibility }
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = description,
                        tint = if (darkTheme) Color.White else Color.Gray,
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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            isError = showError,

        )
    }
    if (showError) {
        Text(
            text = "Forget Password",
            color = Color.Red,
            modifier = Modifier.padding(start = 16.dp),
            fontSize = 12.sp
        )
    }
}


@Composable
@Preview(showSystemUi = true , showBackground = true , uiMode = Configuration.UI_MODE_NIGHT_YES)
fun PasswordPreview()
{
    Password(
        darkTheme = true,
        false,
        onPasswordChanged = {}
    )
}