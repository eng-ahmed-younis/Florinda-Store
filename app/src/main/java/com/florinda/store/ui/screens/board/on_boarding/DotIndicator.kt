package com.florinda.store.ui.screens.board.on_boarding

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.florinda.store.ui.theme.colorPrimary
import com.florinda.store.ui.theme.colorWhite
import okhttp3.internal.isSensitiveHeader


@Composable
fun DotIndicator(
    dotCount: Int,
    selectedIndex: Int
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(dotCount){
            if (it == selectedIndex){
                Box(
                    modifier = Modifier
                        .height(8.dp)
                        .width(22.dp)
                        .clip(CircleShape)
                        .background(if (isSystemInDarkTheme()) colorPrimary else colorWhite)
                )
            }else{
                Box(
                    modifier = Modifier.size(8.dp)
                        .clip(CircleShape)
                        .background(if (isSystemInDarkTheme()) colorPrimary else colorWhite)
                )
            }

            if (it != dotCount - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 3.dp))
            }
        }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DotIndicatorPreview(){
    DotIndicator(3,2)
}
