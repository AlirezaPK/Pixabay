package ir.kodato.pixabay.presentation.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.kodato.pixabay.domain.repository.PixabayRepository
import ir.kodato.pixabay.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: PixabayRepository
) : ViewModel() {

    var state by mutableStateOf(SearchState())

    init {
        searchImages("food")
    }

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.OnSearchQueryChange -> {
                viewModelScope.launch {
                    state = state.copy(query = event.query)
                    delay(500)
                    searchImages(event.query)
                }
            }

            is SearchEvent.ToggleSearch -> {
                state = state.copy(isSearchVisible = !state.isSearchVisible)
            }
        }
    }

    private fun searchImages(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getPixabayImages(query)
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            withContext(Dispatchers.Main) {
                                result.data?.let { images ->
                                    state = state.copy(
                                        images = images,
                                        isLoading = false
                                    )
                                }
                            }
                        }

                        is Resource.Error -> {
                            withContext(Dispatchers.Main) {
                                state = state.copy(
                                    error = result.message,
                                    isLoading = false
                                )
                            }
                        }

                        is Resource.Loading -> {
                            withContext(Dispatchers.Main) {
                                state = state.copy(
                                    isLoading = true,
                                    images = result.data!!
                                )
                            }
                        }
                    }
                }
        }
    }
}