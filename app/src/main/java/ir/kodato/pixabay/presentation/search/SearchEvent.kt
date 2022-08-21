package ir.kodato.pixabay.presentation.search

sealed class SearchEvent {
    data class OnSearchQueryChange(val query: String) : SearchEvent()
    object ToggleSearch : SearchEvent()
}
