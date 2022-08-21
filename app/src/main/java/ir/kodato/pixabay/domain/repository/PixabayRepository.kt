package ir.kodato.pixabay.domain.repository

import ir.kodato.pixabay.domain.model.PixabayImage
import ir.kodato.pixabay.util.Resource
import kotlinx.coroutines.flow.Flow

interface PixabayRepository {

    suspend fun getPixabayImages(
        query: String
    ): Flow<Resource<List<PixabayImage>>>
}