package com.florinda.store.ui.screens.main.home

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.florinda.store.R
import com.florinda.store.app_component.TopAppBarHome
import com.florinda.store.navigation.Router
import com.florinda.store.ui.theme.SpaceLarge
import com.florinda.store.ui.theme.colorBlack
import com.florinda.store.ui.theme.colorBottomBarBackground
import com.florinda.store.ui.theme.colorPrimary
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.runBlocking
import timber.log.Timber

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("MutableCollectionMutableState")
@Composable
fun HomeScreen(
    navController: NavController,
    state: State<HomeState>,
    intentChannel: Channel<HomeIntents>,
    darkTheme: Boolean = isSystemInDarkTheme(),
) {

    val context = LocalContext.current
    var categoryId by rememberSaveable { mutableIntStateOf(0) }


    LaunchedEffect(key1 = Unit) {
        intentChannel.send(HomeIntents.GetCategories)
        intentChannel.send(HomeIntents.GetOrdersByCategory(1))
    }


    LaunchedEffect(key1 = categoryId) {
        Timber.i("launcher LaunchedEffect categoryId ")
        intentChannel.send(HomeIntents.GetOrdersByCategory(categoryId))
    }




    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(if (darkTheme) colorBottomBarBackground else colorPrimary)
            .verticalScroll(rememberScrollState())
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .offset(0.dp, (-40).dp),
            painter = painterResource(id = R.drawable.bg_main),
            contentDescription = "Header Background",
            contentScale = ContentScale.FillWidth
        )

        Column(
            modifier = Modifier
                .padding(20.dp)
        ) {
            TopAppBarHome(
                notificationIconOnClick = {
                    Toast.makeText(context, "notification", Toast.LENGTH_SHORT).show()
                    runBlocking(Dispatchers.IO) {
                        intentChannel.send(HomeIntents.GetCategories)
                    }
                },
                dashboardIconOnClick = {
                    Toast.makeText(context, " dashboard", Toast.LENGTH_SHORT).show()
                }
            )
            Spacer(modifier = Modifier.height(SpaceLarge))
            Title()
            Spacer(modifier = Modifier.height(30.dp))
            if (state.value.categories.isNotEmpty()) {
                Timber.i(
                    "categoriesList current ${
                        state
                            .value.categories.size
                    }"
                )
                HomeScreenContent(
                    categories = state.value.categories,
                    ordersByCategoriesList = state.value.ordersByCategories?.orders ?: emptyList(),
                    onCategoryClicked = {
                        categoryId = it
                    },
                    seeAllClicked = {
                        navController.navigate(Router.OrdersScreen.route)
                    },
                    

                )

            }
        }

    }
}

@Composable
fun Title() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        androidx.compose.material.Text(
            text = "What would you like to\n" +
                    "   " + "eat today ",
            color = colorBlack,
            style = MaterialTheme.typography.h6
        )
    }
}


@Composable
@Preview(showBackground = true, showSystemUi = true)
fun HomeScreenPreview() {
    HomeScreen(
        rememberNavController(),
        state = remember {
            mutableStateOf(
                HomeState(
                    loading = false,
                    "", null, emptyList(), null
                )
            )
        },
        intentChannel = Channel { }
    )
}


@Composable
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun HomeScreenPreviewDark() {
    HomeScreen(
        rememberNavController(),
        state = remember {
            mutableStateOf(
                HomeState(
                    loading = false,
                    "", null, emptyList(), null
                )
            )
        },
        intentChannel = Channel { }
    )
}
