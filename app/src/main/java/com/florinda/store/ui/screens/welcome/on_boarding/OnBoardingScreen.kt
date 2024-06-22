package com.florinda.store.ui.screens.welcome.on_boarding

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.florinda.store.R
import com.florinda.store.navigation.AppScreen
import com.florinda.store.ui.screens.main.LocalNavController
import com.florinda.store.ui.theme.colorBottomBarBackground
import com.florinda.store.ui.theme.colorItemsDark
import com.florinda.store.ui.theme.colorItemsLight
import com.florinda.store.ui.theme.colorPrimary
import com.florinda.store.ui.theme.colorWhite
import kotlinx.coroutines.channels.Channel


@SuppressLint("UnusedContentLambdaTargetStateParameter")
@Composable
fun OnBoardingScreen(
    intentChannel: Channel<OnBoardIntents>,
    darkTheme: Boolean = isSystemInDarkTheme(),
) {

    val onBoardImages = listOf(
        R.drawable.ecommerce_1,
        R.drawable.ecommerce_2,
        R.drawable.ecommerce_3
    )

    var currentPosition by rememberSaveable { mutableIntStateOf(0) }
    var animate by rememberSaveable { mutableStateOf(true) }
    var finish by rememberSaveable { mutableStateOf(false) }
    val navController = LocalNavController.current


    LaunchedEffect(key1 = finish){
        if (finish) {
            intentChannel.send(OnBoardIntents.OnBoardingFinish)
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(if (darkTheme) colorBottomBarBackground else colorPrimary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Spacer(Modifier.windowInsetsTopHeight(WindowInsets.systemBars))

        AnimatedContent(
            targetState = animate,
            modifier = Modifier
                .fillMaxWidth(),
            transitionSpec = {
                slideInHorizontally(
                    initialOffsetX = { value ->
                        value
                    }
                ).togetherWith(
                    slideOutHorizontally(
                        targetOffsetX = { value ->
                            -value
                        }
                    )
                )
            },
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.6f)
                        .padding(top = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "FLORINDA",
                        fontSize = 40.sp,
                        color = if (darkTheme) colorItemsLight else colorItemsDark,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace,
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    when (currentPosition) {
                        0 -> {
                            Text(
                                text = buildAnnotatedString {
                                    append(text = "Welcome to ")
                                    withStyle(
                                        style = SpanStyle(
                                            fontWeight = FontWeight.Bold,
                                            color = if (darkTheme) colorItemsLight else colorItemsDark,
                                            fontSize = 22.sp
                                        )
                                    ) {
                                        append("Florinda.")
                                    }
                                    append(" Let's Shop!")
                                },
                                color = if (darkTheme) colorItemsLight else colorItemsDark,
                                fontSize = 18.sp,
                                textAlign = TextAlign.Center,
                                fontFamily = FontFamily(Font(R.font.muli)),
                            )
                        }

                        1 -> {
                            Text(
                                text = stringResource(id = R.string.ecommerce_title_2),
                                color = if (darkTheme) colorItemsLight else colorItemsDark,
                                fontSize = 18.sp,
                                textAlign = TextAlign.Center
                            )
                        }

                        else -> {
                            Text(
                                text = stringResource(id = R.string.ecommerce_title_3),
                                color = if (darkTheme) colorItemsLight else colorItemsDark,
                                fontSize = 18.sp,
                                textAlign = TextAlign.Center
                            )

                        }
                    }
                    Spacer(modifier = Modifier.height(30.dp))

                    Image(
                        painter = painterResource(id = onBoardImages[currentPosition]),
                        contentDescription = "Splash Image",
                        modifier = Modifier
                            .size(300.dp)
                            .padding(40.dp)
                    )
                }
            }, label = ""
        )


        DotIndicator(onBoardImages.size, currentPosition)

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(start = 30.dp, end = 30.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = if (darkTheme) colorPrimary else colorWhite
            ),
            shape = RoundedCornerShape(16.dp),
            onClick = {
                //0,1
                if (currentPosition < 2) {
                    currentPosition++
                    animate = !animate
                    //2
                } else {
                    finish = true
                    navController.popBackStack()
                    navController.navigate(AppScreen.WelcomeGraph.WelcomeScreen.route)
                }
            }

        ) {
            Text(
                text = if (currentPosition < 2) "Continue" else "Next",
                modifier = Modifier,
            )
        }

    }
}


@Composable
@Preview(showBackground = true)
fun OnBoardingScreenPreview() {
    OnBoardingScreen(
        Channel {  }
    )
}

@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun OnBoardingScreenPreviewDark() {
    OnBoardingScreen(
        Channel {  }
    )
}

