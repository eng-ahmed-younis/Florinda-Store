package com.florinda.store.ui.screens.search

import android.content.res.Configuration
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.outlined.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.florinda.store.ui.screens.orders.OrderCard
    import com.florinda.store.ui.theme.colorBottomBarBackground
import com.florinda.store.ui.theme.colorItemsDark
import com.florinda.store.ui.theme.colorItemsLight
import com.florinda.store.ui.theme.colorPrimary
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch


@Composable
fun SearchScreen(
    navController: NavController,
    state: State<SearchState>,
    intentSearchChannel: Channel<SearchIntents>,
    darkTheme: Boolean = isSystemInDarkTheme(),
) {

    val scope = rememberCoroutineScope()
    var searchQuery by rememberSaveable { mutableStateOf("") }


    LaunchedEffect(key1 = Unit) {
        intentSearchChannel.send(SearchIntents.GetOrders)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(if (darkTheme) colorBottomBarBackground else colorPrimary),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
            ,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(
                onClick = {
                    navController.navigateUp()
                },
                modifier = Modifier
                    .padding(start = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBackIos,
                    contentDescription = "back button",
                    tint = if (darkTheme) colorItemsLight else colorItemsDark,
                )
            }

            TextField(
                value = searchQuery,
                onValueChange = {
                    searchQuery = it
                    scope.launch {
                        intentSearchChannel.send(SearchIntents.SearchByName(searchQuery))
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()

                    .padding(start = 4.dp, end = 8.dp)
                    .border(
                        width = 1.dp,
                        color = if (darkTheme) colorItemsLight else colorItemsDark,
                        shape = RoundedCornerShape(16.dp)
                    ),
                shape = RoundedCornerShape(8.dp),
                label = {
                    Text(
                        text = "search For Orders",
                        fontSize = 16.sp,
                        color = if (darkTheme) colorItemsLight else colorItemsDark,
                    )
                },
                trailingIcon = {
                    Row(
                        modifier = Modifier
                            .wrapContentWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Canvas(
                            modifier = Modifier
                                .height(24.dp)
                                .padding(start = 10.dp, end = 5.dp)
                        ) {
                            drawLine(
                                color = if (darkTheme) Color.White else Color.Gray,
                                start = Offset(0f, 0f),
                                end = Offset(0f, size.height),
                                strokeWidth = 2.0f
                            )
                        }
                        Icon(
                            imageVector = Icons.Default.Mic,
                            contentDescription = "",
                            tint = if (darkTheme) Color.White else Color.Gray,
                            modifier = Modifier.padding(end = 10.dp)
                        )

                    }
                },
                colors = TextFieldDefaults
                    .textFieldColors(
                        textColor = if (darkTheme) colorItemsLight else colorItemsDark,
                        cursorColor = if (darkTheme) colorItemsLight else colorItemsDark,
                        disabledTextColor = Color.Transparent,
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                enabled = true,
                singleLine = true
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            state.value.searchOrdersResult.let {
                items(it.size) { index ->
                    OrderCard(order = it[index],darkTheme = darkTheme)
                }
            }
        }
    }

}


@Composable
@Preview(showBackground = true, showSystemUi = true, locale = "en")
fun SearchScreenPreview() {
    SearchScreen(
        navController = rememberNavController(),
        state = remember {
            mutableStateOf(
                SearchState(false, emptyList(), emptyList(),null)
            )
        },
        intentSearchChannel = Channel { }
    )
}

@Composable
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun SearchScreenPreviewDark() {
    SearchScreen(
        navController = rememberNavController(),
        state = remember {
            mutableStateOf(
                SearchState(false, emptyList(), emptyList(),null)
            )
        },
        intentSearchChannel = Channel { }
    )
}





