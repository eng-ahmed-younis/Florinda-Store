package com.florinda.store.ui.screens.bottom.home

import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.florinda.domain.model.CategoryModel
import com.florinda.domain.model.OrderModel
import com.florinda.store.R
import com.florinda.store.ui.screens.bottom.home.composable.FilterBottomSheet
import com.florinda.store.ui.theme.SpaceLarge
import com.florinda.store.ui.theme.SpaceMedium
import com.florinda.store.ui.theme.colorBlack
import com.florinda.store.ui.theme.colorItemsDark
import com.florinda.store.ui.theme.colorItemsLight
import com.florinda.store.ui.theme.colorPrimary
import com.florinda.store.ui.theme.colorRedDark
import com.florinda.store.ui.theme.colorWhite
import timber.log.Timber

@Composable
fun HomeScreenContent(
    categories: List<CategoryModel>,
    ordersByCategoriesList: List<OrderModel>,
    onCategoryClicked: (Int) -> Unit,
    seeAllClicked: () -> Unit,
) {

    val context = LocalContext.current
    var showSheet by remember { mutableStateOf(false) }


    if (showSheet) {
        FilterBottomSheet(
            onDismiss = { showSheet = false }
        )
    }


    Column {
        HeaderSearch(
            searchTitleChange = {},
            filterOnClick = {
             showSheet = true
            }
        )
        Spacer(modifier = Modifier.height(SpaceMedium))
        CategorySection(
            categories,
            context,
            onCategoryClicked = {
                onCategoryClicked(it)
            },
            seeAllClicked = {
                seeAllClicked()
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        PopularSection(ordersByCategoriesList, context)

    }
}


@Composable
fun HeaderSearch(
    searchTitleChange: (String) -> Unit,
    filterOnClick: () -> Unit
) {
    var searchText by rememberSaveable { mutableStateOf("") }
    LaunchedEffect(key1 = searchText) {
        searchTitleChange(searchText)
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .height(50.dp),
            shape = RoundedCornerShape(SpaceLarge),
            elevation = 4.dp
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 5.dp),
                value = searchText,
                onValueChange = {
                    searchText = it
                },
                placeholder = {
                    Text(
                        text = stringResource(R.string.search_product),
                        modifier = Modifier
                            .align(Alignment.Start),
                        color = Color.Gray
                    )
                },
                shape = RoundedCornerShape(24.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "searching",
                        tint = Color.Gray
                    )
                },
                trailingIcon = {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(colorRedDark)
                    ) {
                        IconButton(
                            onClick = {
                                filterOnClick()
                            }) {
                            Icon(
                                imageVector = Icons.Filled.FilterList,
                                contentDescription = "filter list",
                                tint = Color.White
                            )
                        }
                    }
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = colorWhite,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = colorPrimary

                )
            )
        }

    }
}


@Composable
fun CategorySection(
    categories: List<CategoryModel>,
    context: Context,
    onCategoryClicked: (Int) -> Unit,
    seeAllClicked: () -> Unit,
    darkTheme: Boolean = isSystemInDarkTheme(),
) {
    var currentCategory by rememberSaveable { mutableIntStateOf(0) }


    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Categories",
                color = if (darkTheme) colorItemsLight else colorItemsDark,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(start = 5.dp)
            )
            TextButton(
                onClick = {
                    seeAllClicked()
                }
            ) {
                Text(
                    text = "See all",
                    color = if (darkTheme) colorRedDark else colorWhite,
                )
                Icon(
                    imageVector = Icons.Outlined.KeyboardArrowRight,
                    contentDescription = "Localized description",
                    modifier = Modifier.padding(end = 8.dp),
                    tint = if (darkTheme) colorRedDark else colorWhite,

                    )
            }

        }


        LazyRow(modifier = Modifier.fillMaxWidth()) {
            items(categories.size) { index ->
                val color: Color = if (isSystemInDarkTheme()) {
                    if (currentCategory == index) colorPrimary else colorWhite
                } else {
                    if (currentCategory == index) colorBlack else colorWhite

                }

                Box(
                    modifier = Modifier
                        .height(50.dp)
                        .clip(RoundedCornerShape(24.dp))
                        .background(
                            color = color
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 2.dp)
                            .clickable {
                                currentCategory = index
                                onCategoryClicked(categories[index].id)
                            },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {

                        AsyncImage(

                            model = ImageRequest.Builder(context)
                                .data(categories[index].image)
                                //.diskCachePolicy(CachePolicy.ENABLED)
                                //.memoryCachePolicy(CachePolicy.ENABLED)
                                .crossfade(true)
                                .build(),
                            contentDescription = "",
                            modifier = Modifier
                                .size(60.dp, 60.dp)
                                .padding(start = 20.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Fit
                        )
                        Text(
                            modifier = Modifier
                                .padding(
                                    start = 5.dp,
                                    end = 16.dp,
                                    top = 8.dp,
                                    bottom = 8.dp
                                ),
                            text = categories[index].name,
                            color = if (index == currentCategory) colorWhite else Color.Black

                        )
                    }
                }

                Spacer(modifier = Modifier.width(10.dp))

            }
        }
    }
}


@Composable
fun PopularSection(
    orders: List<OrderModel>,
    context: Context,
    darkTheme: Boolean = isSystemInDarkTheme(),

    ) {
    Column {

        Text(
            text = "Popular now \uD83D\uDD25",
            style = MaterialTheme.typography.h6,
            color = if (darkTheme) colorItemsLight else colorItemsDark
        )

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.spacedBy(10.dp), content = {
                items(orders.size) { index ->
                    Box(
                        modifier = Modifier
                            .width(200.dp)
                            .height(350.dp)
                            .padding(10.dp)
                            .clip(RoundedCornerShape(24.dp))
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(context)
                                .data(orders[index].images.first())
                                .diskCachePolicy(CachePolicy.ENABLED)
                                .memoryCachePolicy(CachePolicy.ENABLED)
                                .crossfade(true)
                                .build(),
                            contentDescription = "",
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .blur(11.dp),
                            contentScale = ContentScale.Crop
                        )


                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .padding(top = 20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            AsyncImage(
                                model = ImageRequest.Builder(context)
                                    .data(orders[index].images.first())
                                    .diskCachePolicy(CachePolicy.ENABLED)
                                    .memoryCachePolicy(CachePolicy.ENABLED)
                                    .crossfade(true)
                                    .build(),
                                contentDescription = "",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(150.dp)
                                    .padding(start = 20.dp, end = 20.dp)
                                    .clip(CircleShape),
                                contentScale = ContentScale.Fit
                            )

                            Text(
                                text = orders[index].title,
                                fontWeight = FontWeight.Bold,
                                color = if (darkTheme) colorItemsLight else colorItemsDark,
                                modifier = Modifier
                                    .padding(start = 5.dp, end = 5.dp)
                            )

                            Spacer(modifier = Modifier.height(SpaceLarge))
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(20.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = buildAnnotatedString {
                                        withStyle(
                                            style = SpanStyle(
                                                colorBlack,
                                                fontWeight = FontWeight.Normal
                                            )
                                        ) {
                                            append(orders[index].price.toString())
                                        }
                                        withStyle(
                                            style = SpanStyle(
                                                colorBlack
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


                }
            }
        )

    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun HeaderSearchPreview() {
    HomeScreenContent(emptyList(), emptyList(), {}, {})
}

@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun HeaderSearchPreviewDark() {
    HomeScreenContent(emptyList(), emptyList(), {}, {})
}