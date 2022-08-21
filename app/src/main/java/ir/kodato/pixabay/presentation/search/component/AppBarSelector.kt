package ir.kodato.pixabay.presentation.search.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi

@ExperimentalComposeUiApi
@Composable
fun AppBarSelector(
    searchState: Boolean,
    queryState: String,
    onQueryChanged: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
    onSearchTriggered: () -> Unit
) {
    if (searchState) {
        SearchAppBar(
            query = queryState,
            onQueryChanged = onQueryChanged,
            onCloseClicked = onCloseClicked,
            onSearchClicked = onSearchClicked
        )
    } else {
        AppBar(
            onSearchClicked = { onSearchTriggered() }
        )
    }
}