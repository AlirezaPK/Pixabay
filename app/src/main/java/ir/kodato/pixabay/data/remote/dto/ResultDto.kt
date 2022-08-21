package ir.kodato.pixabay.data.remote.dto

data class ResultDto(
    val hits: List<HitDto>,
    val total: Int,
    val totalHits: Int
)