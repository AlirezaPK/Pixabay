package ir.kodato.pixabay.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PixabayImageEntity(
    @PrimaryKey val id: Int? = null,
    val comments: Int,
    val downloads: Int,
    val webformatURL:String,
    val largeImageURL: String,
    val likes: Int,
    val tags: String,
    val user: String,
    val userImageURL: String
)
