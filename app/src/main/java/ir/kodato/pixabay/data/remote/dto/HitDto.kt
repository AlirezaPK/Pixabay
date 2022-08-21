package ir.kodato.pixabay.data.remote.dto

data class HitDto(
    val comments: Int,
    val downloads: Int,
    val id: Int,
    val webformatURL:String,
    val largeImageURL: String,
    val likes: Int,
    val tags: String,
    val user: String,
    val userImageURL: String,
)