package com.florinda.store.ui.screens.board.welcome

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.florinda.store.R
import com.florinda.store.app_component.ScreenAppBar
import com.florinda.store.navigation.Router
import com.florinda.store.ui.screens.main.LocalNavController
import com.florinda.store.ui.theme.colorBackgroundDark
import com.florinda.store.ui.theme.colorBottomBarBackground
import com.florinda.store.ui.theme.colorItemsDark
import com.florinda.store.ui.theme.colorItemsLight
import com.florinda.store.ui.theme.colorPrimary
import com.florinda.store.ui.theme.colorRedDark
import kotlinx.coroutines.channels.Channel

@Composable
fun WelcomeScreen(
    state : State<WelcomeState>,
    intentChannel : Channel<WelcomeIntents>,
    darkTheme: Boolean = isSystemInDarkTheme(),
) {
    val navController = LocalNavController.current

    LaunchedEffect(key1 = Unit){
        intentChannel.send(WelcomeIntents.GetCurrentUser)
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(if (darkTheme) colorBottomBarBackground else colorPrimary)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(Modifier.windowInsetsTopHeight(WindowInsets.systemBars))

        ScreenAppBar(
            modifier = Modifier.padding(top = 20.dp),
            screenTitle = "Welcome",
            onBackClicked = { navController.popBackStack() }
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(10.dp),
            verticalArrangement = Arrangement.Center,
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
                    .height(330.dp)
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
                        style = MaterialTheme.typography.h6
                    )
                    Text(
                        text = "Let’s order right now",
                        color = if (darkTheme) colorItemsLight else colorItemsDark,
                        style = MaterialTheme.typography.h6
                    )
                    Text(
                        text = "\n" +
                                "The fastest delivery service in the\n" +
                                "town, start ordering now\n",
                        color = if (darkTheme) colorItemsLight else colorItemsDark,
                        style = MaterialTheme.typography.button,
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Button(
                        onClick = {
                            navController.popBackStack()
                            if(state.value.currentUser == null){
                                navController.popBackStack()
                                navController.navigate(Router.LoginScreen.route)
                            }else{
                                navController.popBackStack()
                                navController.navigate(Router.HomeScreen.route)
                            }
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
                            style = MaterialTheme.typography.button,
                            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                        )
                    }
                }
            }
        }
    }
}


@Composable
@Preview
fun WelcomeScreenPreview() {
    WelcomeScreen(
        state = remember{
            mutableStateOf(WelcomeState(false,null,null ))
        },
        Channel {  }
    )
}



@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
fun WelcomeScreenDarkPreview() {
    WelcomeScreen(
        state = remember{
            mutableStateOf(WelcomeState(false,null,null ))
        },
        Channel {  }
    )
}
