package com.artemissoftware.daedalusscheduler.ui.screens.list

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.artemissoftware.daedalusscheduler.R
import com.artemissoftware.daedalusscheduler.data.models.Priority
import com.artemissoftware.daedalusscheduler.ui.theme.topAppBarBackgroundColor
import com.artemissoftware.daedalusscheduler.ui.theme.topAppBarContentColor

@Composable
fun ListAppBar(
){
    DefaultListAppBar(
        onSearchClicked = {},
        onSortClicked = {},
        onDeleteClicked = {}
    )
}

@Composable
fun DefaultListAppBar(
    onSearchClicked: () -> Unit,
    onSortClicked: (Priority) -> Unit,
    onDeleteClicked: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.list_screen_title),
                color = MaterialTheme.colors.topAppBarContentColor
            )
        },
//        actions = {
//            ListAppBarActions(
//                onSearchClicked = onSearchClicked,
//                onSortClicked = onSortClicked,
//                onDeleteClicked = onDeleteClicked
//            )
//        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor
    )
}


@Composable
@Preview
private fun DefaultListAppBarPreview() {
    DefaultListAppBar(
        onSearchClicked = {},
        onSortClicked = {},
        onDeleteClicked = {}
    )
}