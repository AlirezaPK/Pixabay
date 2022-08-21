package ir.kodato.pixabay.domain.model

data class Result(
    val hits: List<Hit>,
    val total: Int,
    val totalHits: Int
)