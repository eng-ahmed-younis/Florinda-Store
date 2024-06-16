package com.florinda.store.ui.screens.main.home.composable

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.florinda.store.R
import com.florinda.store.ui.theme.colorBottomBarBackground
import com.florinda.store.ui.theme.colorItemsDark
import com.florinda.store.ui.theme.colorItemsLight
import com.florinda.store.ui.theme.colorPrimary


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterBottomSheet(
    onDismiss: () -> Unit
) {
    val modalBottomSheetState = rememberModalBottomSheetState()
    val context = LocalContext.current
    val filtersList = mutableListOf(
        stringResource(id = R.string.filter_by_title),
        stringResource(id = R.string.filter_by_category),
        stringResource(id = R.string.filter_by_price),
        stringResource(id = R.string.filter_by_price_range)
    )

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
        containerColor = if (isSystemInDarkTheme()) colorBottomBarBackground else colorPrimary

    ) {
        LazyColumn(
            horizontalAlignment = Alignment.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp, horizontal = 15.dp)
        ) {
            items(filtersList.size) {
                OutlinedButton(
                    onClick = {
                              Toast.makeText(context , filtersList[it] , Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier.padding(2.dp)
                ) {
                    Text(
                        text = filtersList[it],
                        color = if (isSystemInDarkTheme()) colorItemsLight else colorItemsDark,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.muli))
                    )
                }
            }

        }

    }
}