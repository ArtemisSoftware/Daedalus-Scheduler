package com.artemissoftware.daedalusscheduler.ui.screens.task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.artemissoftware.daedalusscheduler.data.models.Priority
import com.artemissoftware.daedalusscheduler.ui.theme.LARGE_PADDING
import com.artemissoftware.daedalusscheduler.R
import com.artemissoftware.daedalusscheduler.components.PriorityDropDown
import com.artemissoftware.daedalusscheduler.ui.theme.MEDIUM_PADDING
import com.artemissoftware.daedalusscheduler.util.Constants.MAX_TITLE_LENGTH


@Composable
fun TaskContent(
    title: String,
    onTitleChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    priority: Priority,
    onPrioritySelected: (Priority) -> Unit
) {


    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    var textFieldValueState = remember {
        mutableStateOf(
            ""
        )
    }

    val maxChar = 3


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(all = LARGE_PADDING)
            .focusRequester(focusRequester)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = title,
            onValueChange = {

                onTitleChange(it)

                if (it.length >= MAX_TITLE_LENGTH) {
                    //Work around to bug on limit characters
                    focusManager.clearFocus()
                    //focusRequester.requestFocus()
                }

            },
            label = { Text(text = stringResource(id = R.string.title)) },
            textStyle = MaterialTheme.typography.body1,
            singleLine = true,
            maxLines = 1
        )
        Divider(
            modifier = Modifier.height(MEDIUM_PADDING),
            color = MaterialTheme.colors.background
        )
        PriorityDropDown(
            priority = priority,
            onPrioritySelected = onPrioritySelected
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxSize(),
            value = description,
            onValueChange = { onDescriptionChange(it) },
            label = { Text(text = stringResource(id = R.string.description)) },
            textStyle = MaterialTheme.typography.body1
        )
    }
}


@Composable
@Preview
private fun TaskContentPreview() {
    TaskContent(
        title = "",
        onTitleChange = {},
        description = "",
        onDescriptionChange = {},
        priority = Priority.LOW,
        onPrioritySelected = {}
    )
}

