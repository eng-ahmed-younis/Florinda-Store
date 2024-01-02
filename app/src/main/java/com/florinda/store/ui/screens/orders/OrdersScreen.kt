package com.florinda.store.ui.screens.orders

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.florinda.domain.model.OrderModel
import com.florinda.store.navigation.Router
import com.florinda.store.ui.screens.orders.composables.OrdersHeader
import com.florinda.store.ui.theme.colorBlack
import com.florinda.store.ui.theme.colorBottomBarBackground
import com.florinda.store.ui.theme.colorItemsDark
import com.florinda.store.ui.theme.colorItemsLight
import com.florinda.store.ui.theme.colorPrimary
import com.florinda.store.ui.theme.colorWhite
import kotlinx.coroutines.channels.Channel

@Composable
fun OrdersScreen(
    navController: NavController,
    state: State<OrdersState>,
    intentOrdersChannel: Channel<OrdersIntents>,
    darkTheme: Boolean = isSystemInDarkTheme(),
) {


    LaunchedEffect(key1 = Unit) {
        intentOrdersChannel.send(OrdersIntents.GetOrders)
    }

    if (state.value.isLoading) {
        CircularProgressIndicator(color = if (darkTheme) colorItemsLight else colorItemsDark)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(if (darkTheme) colorBottomBarBackground else colorPrimary)
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        OrdersHeader(
            searchButtonClick = {
                navController.navigate(Router.SearchScreen.route)
            },
            backClicked = { navController.popBackStack() }
        )
        Spacer(modifier = Modifier.height(15.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(space = 16.dp), // [top,bottom] margin between each item
            horizontalArrangement = Arrangement.spacedBy(space = 16.dp), // [left,right] margin between each item
            contentPadding = PaddingValues(all = 10.dp), //margin for all layout
        ) {
            state.value.data?.let {
                items(it.size) { index ->
                    OrderCard(order = it[index], darkTheme = darkTheme)
                }
            }

        }

    }
}


@Composable
fun OrderCard(
    order: OrderModel,
    darkTheme: Boolean
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(5.dp)
    ) {
        ImageCard(order = order)

        Text(
            text = order.title,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            color = if (darkTheme) colorItemsLight else colorItemsDark,
            maxLines = 1,
            modifier = Modifier
                .padding(5.dp)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            if (darkTheme) colorItemsLight else colorItemsDark,
                            fontWeight = FontWeight.Normal
                        )
                    ) {
                        append(order.price.toString())
                    }
                    withStyle(
                        style = SpanStyle(
                            if (darkTheme) colorItemsLight else colorItemsDark
                        )
                    ) {
                        append("$")
                    }
                },
                style = MaterialTheme.typography.h6
            )


            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(colorBlack)
                    .padding(4.dp)
                    .clickable {

                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(20.dp, 20.dp),
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                    tint = colorWhite
                )
            }

        }
    }
}


@Composable
fun ImageCard(order: OrderModel?) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .background(Color.Transparent)
            .clip(RoundedCornerShape(24.dp))
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(order?.images?.first())
                .crossfade(true)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .build(),
            contentDescription = "order image",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .clip(RectangleShape),
            contentScale = ContentScale.Crop
        )
    }
}


@Composable
@Preview(showBackground = true)
fun OrderCardPreview() {
    ImageCard(null)
}


@Composable
@Preview(showBackground = true, showSystemUi = true)
fun OrdersScreenPreview() {
    OrdersScreen(
        navController = rememberNavController(),
        remember {
            mutableStateOf(
                OrdersState(false, emptyList(), null)
            )
        },
        Channel { }
    )
}


@Composable
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun OrdersScreenPreviewDark() {
    OrdersScreen(
        navController = rememberNavController(),
        remember {
            mutableStateOf(
                OrdersState(false, emptyList(), null)
            )
        },
        Channel { }
    )
}