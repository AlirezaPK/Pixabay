package ir.kodato.pixabay.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PixabayImage(
    val comments: Int,
    val downloads: Int,
    val webformatURL:String,
    val largeImageURL: String,
    val likes: Int,
    val tags: String,
    val user: String,
    val userImageURL: String,
):Parcelable
