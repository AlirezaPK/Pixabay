package ir.kodato.pixabay.data.mapper

import ir.kodato.pixabay.data.local.PixabayImageEntity
import ir.kodato.pixabay.data.remote.dto.HitDto
import ir.kodato.pixabay.data.remote.dto.ResultDto
import ir.kodato.pixabay.domain.model.Hit
import ir.kodato.pixabay.domain.model.PixabayImage
import ir.kodato.pixabay.domain.model.Result

fun HitDto.toPixabayImageEntity(): PixabayImageEntity {
    return PixabayImageEntity(
        comments = comments,
        downloads = downloads,
        id = id,
        webformatURL = webformatURL,
        largeImageURL = largeImageURL,
        likes = likes,
        tags = tags,
        user = user,
        userImageURL = userImageURL
    )
}

fun PixabayImageEntity.toPixabayImage(): PixabayImage {
    return PixabayImage(
        comments = comments,
        downloads = downloads,
        webformatURL = webformatURL,
        largeImageURL = largeImageURL,
        likes = likes,
        tags = tags,
        user = user,
        userImageURL = userImageURL
    )
}