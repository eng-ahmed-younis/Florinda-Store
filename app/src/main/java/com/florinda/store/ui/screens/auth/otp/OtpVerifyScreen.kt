package com.florinda.store.ui.screens.auth.otp

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.florinda.store.R
import com.florinda.store.ui.screens.main.LocalTheme
import com.florinda.store.ui.theme.SpaceSmall
import com.florinda.store.ui.theme.SpaceXLarge
import com.florinda.store.ui.theme.colorBlack
import com.florinda.store.ui.theme.colorBottomBarBackground
import com.florinda.store.ui.theme.colorItemsDark
import com.florinda.store.ui.theme.colorItemsLight
import com.florinda.store.ui.theme.colorPrimary
import com.florinda.store.ui.theme.colorRedLite
import com.florinda.store.ui.theme.colorWhite

@Composable
fun OtpVerifyScreen(
) {

    val darkTheme = LocalTheme.current.isDark
    val code by rememberSaveable { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(if (darkTheme) colorBottomBarBackground else colorPrimary)
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OTPIcon()
            Spacer(modifier = Modifier.height(SpaceXLarge))
            VerificationCodeText(darkTheme = darkTheme)
            VerificationCodeNumber(darkTheme)
            Spacer(modifier = Modifier.height(SpaceSmall))


        }
    }
}

@Composable
fun Field(
    modifier: Modifier = Modifier,
    onValueChanged: (String, String) -> String = { _, new -> new }
) {
    var state by rememberSaveable { mutableStateOf("") }

    OutlinedTextField(
        modifier = modifier
            .requiredWidth(60.dp)
            .border(
                width = 2.dp,
                color = colorWhite,
                shape = RoundedCornerShape(8.dp)
            ),
        value = state,
        onValueChange = {
            val value = onValueChanged(state, it)
            state = value
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        )
    )
}


@Composable
fun VerificationCode(darkTheme: Boolean) {
    val codes = mutableListOf<String>()
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Field(onValueChanged = { old, new ->
            if (new.length > 1 || new.any { !it.isDigit() }) old else new
        })
        Field(onValueChanged = { old, new ->
            if (new.length > 1 || new.any { !it.isDigit() }) old else new
        })
        Field(onValueChanged = { old, new ->
            if (new.length > 1 || new.any { !it.isDigit() }) old else new
        })
        Field(onValueChanged = { old, new ->
            if (new.length > 1 || new.any { !it.isDigit() }) old else new
        })



        SubmitButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            darkTheme = darkTheme,
            ) {

        }
    }
}

@Composable
fun SubmitButton(
    modifier: Modifier = Modifier,
    darkTheme: Boolean ,
    onClick: () -> Unit
) {
    Button(
        onClick = {
            onClick()
        },
        colors = ButtonDefaults.buttonColors( backgroundColor = if (darkTheme) colorPrimary else colorBlack),
        modifier = modifier,
        shape = RoundedCornerShape(24.dp)
    ) {
        Text(
            text = "Submit",
            color = colorWhite,
            style = MaterialTheme.typography.button,
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
        )
    }
}


@Composable
fun OTPIcon() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(100.dp)
            .clip(shape = CircleShape)
            .background(colorRedLite)
    ) {
        Image(
            painter = painterResource(id = R.drawable.otp_icon),
            contentDescription = "OTP icon",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}


@Composable
fun VerificationCodeText(darkTheme: Boolean) {
    Text(
        text = "Verification code",
        color = if (darkTheme) colorItemsLight else colorItemsDark,
        style = MaterialTheme.typography.h6,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun VerificationCodeNumber(darkTheme: Boolean) {
    Text(
        text = """" We sent you a verification code to your 
                mobile number """,
        color = if (darkTheme) colorItemsLight else colorItemsDark,
        style = MaterialTheme.typography.button,
    )
}




@Composable
@Preview(showBackground = true, showSystemUi = true)
fun OtpVerifyScreenPreview() {
    OtpVerifyScreen()
}

@Composable
@Preview(showSystemUi = true, showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun OtpVerifyScreenPreviewDark() {
    OtpVerifyScreen()
}




