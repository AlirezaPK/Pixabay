package ir.kodato.pixabay.presentation.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import ir.kodato.pixabay.R
import ir.kodato.pixabay.presentation.destinations.DetailScreenDestination
import ir.kodato.pixabay.presentation.search.component.AppBarSelector
import ir.kodato.pixabay.presentation.search.component.ImageItem
import ir.kodato.pixabay.ui.theme.Black
import ir.kodato.pixabay.ui.theme.White

@ExperimentalComposeUiApi
@Destination(start = true)
@Composable
fun SearchScreen(
    navigator: DestinationsNavigator,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val context = LocalContext.current

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppBarSelector(
                searchState = state.isSearchVisible,
                queryState = state.query,
                onQueryChanged = {
                    viewModel.onEvent(
                        SearchEvent.OnSearchQueryChange(query = it)
                    )
                },
                onCloseClicked = {
                    viewModel.onEvent(SearchEvent.ToggleSearch)
                },
                onSearchClicked = {
                    viewModel.onEvent(
                        SearchEvent.OnSearchQueryChange(query = it)
                    )
                },
                onSearchTriggered = {
                    viewModel.onEvent(SearchEvent.ToggleSearch)
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Black),
                contentAlignment = Alignment.Center
            ) {
                if (state.isLoading && state.images.isEmpty()) {
                    CircularProgressIndicator(
                        color = White
                    )
                } else {
                    LazyColumn {
                        items(state.images) {
                            ImageItem(
                                context = context,
                                image = it.webformatURL,
                                username = it.user,
                                tags = it.tags.split(","),
                                onImageClicked = {
                                    navigator.navigate(DetailScreenDestination(it))
                                }
                            )
                        }
                    }
                }

                if (state.error?.isNotEmpty() == true && state.images.isEmpty()) {
                    Image(
                        modifier = Modifier.size(200.dp),
                        painter = painterResource(id = R.drawable.illustration),
                        contentDescription = "illustration"
                    )
                }
            }
        }
    }
}