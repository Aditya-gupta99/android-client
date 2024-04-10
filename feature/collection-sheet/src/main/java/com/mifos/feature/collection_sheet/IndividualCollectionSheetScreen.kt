@file:OptIn(ExperimentalFoundationApi::class)

package com.mifos.feature.collection_sheet

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mifos.core.designsystem.component.MifosScaffold
import com.mifos.core.designsystem.component.MifosScrollableTabRow
import com.mifos.core.designsystem.utility.TabContent


@Composable
fun IndividualCollectionSheetScreen(onBackPressed: () -> Unit) {

    val tabContent by remember { mutableStateOf(listOf<TabContent>(TabContent(tabName = "NEW",{}),TabContent(tabName = "SAVED",{}))) }

    val pagerState =
        rememberPagerState(pageCount = { IndividualCollectionSheetScreenContents.entries.size })

    MifosScaffold(
        icon = Icons.Rounded.ArrowBackIosNew,
        title = "Individual Collection Sheet",
        onBackPressed = onBackPressed,
        actions = {},
        snackbarHostState = null,
        bottomBar = {}
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            MifosScrollableTabRow(
                tabContents = tabContent,
                pagerState = pagerState
            )
        }
    }
}

enum class IndividualCollectionSheetScreenContents {
    NEW,
    SAVED
}

@Preview(showBackground = true)
@Composable
private fun IndividualCollectionSheetScreenPreview() {
    IndividualCollectionSheetScreen { }
}