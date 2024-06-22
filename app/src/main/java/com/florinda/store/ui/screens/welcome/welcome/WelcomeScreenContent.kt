package com.florinda.store.ui.screens.welcome.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.florinda.store.R
import com.florinda.store.app_component.ScreenAppBar
import com.florinda.store.ui.screens.main.LocalTheme
import com.florinda.store.ui.theme.colorBackgroundDark
import com.florinda.store.ui.theme.colorBottomBarBackground
import com.florinda.store.ui.theme.colorItemsDark
import com.florinda.store.ui.theme.colorItemsLight
import com.florinda.store.ui.theme.colorPrimary
import com.florinda.store.ui.theme.colorRedDark

@Composable
fun WelcomeScreenContent(
    onBackClicked: () -> Unit,
    getOrderClicked: () -> Unit,
    ) {

    val darkTheme = LocalTheme.current.isDark

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(if (darkTheme) colorBottomBarBackground else colorPrimary)
            .verticalScroll(rememberScrollState()),
    ) {
        Column {
            Spacer(Modifier.windowInsetsTopHeight(WindowInsets.systemBars))
            ScreenAppBar(
                screenTitle = "Welcome",
                onBackClicked = { onBackClicked() }
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.BottomCenter)
                .padding(10.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    // maximum width to be equal to the [maximum width] multiplied by fraction
                    // by default fraction = 1 then 1*.9 = .9 of full size
                    .wrapContentWidth()
                    .fillMaxHeight()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.welcome),
                    contentDescription = "welcome screen",
                    modifier = Modifier
                        .width(120.dp)
                        .height(120.dp),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
                    .padding(10.dp),
                shape = RoundedCornerShape(25.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(if (darkTheme) colorBackgroundDark else Color.White),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "logo",
                        modifier = Modifier.size(100.dp)
                    )
                    Text(
                        text = "Need to Shop ?",
                        color = if (darkTheme) colorItemsLight else colorItemsDark,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp
                        )
                    )
                    Text(
                        text = "Let’s order right now",
                        color = if (darkTheme) colorItemsLight else colorItemsDark,
                        style = TextStyle(
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp,
                            letterSpacing = 2.sp
                        ),
                        modifier = Modifier
                            .padding(top = 2.dp)
                    )
                    Text(
                        text = "\n" +
                                "The fastest delivery service in the\n" +
                                "town, start ordering now\n",
                        color = if (darkTheme) colorItemsLight else colorItemsDark,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Button(
                        onClick = {
                            getOrderClicked()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = colorRedDark),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .align(Alignment.CenterHorizontally)
                            .padding(start = 40.dp, end = 40.dp),
                        shape = RoundedCornerShape(24.dp)
                    )
                    {
                        Text(
                            text = "Let’s order",
                            color = if (darkTheme) colorItemsLight else colorItemsDark,
                            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun WelcomeContentPreview() {
    WelcomeScreenContent(
        {},
        {}
    )
}