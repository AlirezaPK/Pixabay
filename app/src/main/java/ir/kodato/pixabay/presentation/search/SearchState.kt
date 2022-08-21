package ir.kodato.pixabay.presentation.search

import ir.kodato.pixabay.domain.model.PixabayImage

data class SearchState(
    val images: List<PixabayImage> = listOf(),
    val query: String = "",
    val isSearchVisible: Boolean = false,
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)
