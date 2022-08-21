package ir.kodato.pixabay.data.repository

import android.util.Log
import androidx.room.withTransaction
import ir.kodato.pixabay.data.local.PixabayDatabase
import ir.kodato.pixabay.data.mapper.toPixabayImageEntity
import ir.kodato.pixabay.data.mapper.toPixabayImage
import ir.kodato.pixabay.data.remote.PixabayApi
import ir.kodato.pixabay.domain.model.PixabayImage
import ir.kodato.pixabay.domain.repository.PixabayRepository
import ir.kodato.pixabay.util.Resource
import ir.kodato.pixabay.util.networkBoundResource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PixabayRepositoryImpl @Inject constructor(
    private val api: PixabayApi,
    private val db: PixabayDatabase
) : PixabayRepository {
    private val dao = db.dao

    override suspend fun getPixabayImages(query: String):Flow<Resource<List<PixabayImage>>> = networkBoundResource(
        query = {
            dao.getPixabayImages().map { it.map { it.toPixabayImage() } }
        },
        fetch = {
            delay(2000)
            api.getPhotos(query = query)
        },
        saveFetchResult = { images ->
            db.withTransaction {
                dao.clearPixabayImage()
                dao.insertPixabayImage(images.hits.map { it.toPixabayImageEntity() })
            }
        }
    )

    /*private val dao = db.dao

    override suspend fun getPixabayImages(
        query: String
    ): Flow<Resource<List<PixabayImage>>> = flow {
        emit(Resource.Loading())

        val localResult = dao.getPixabayImages().map { it.toPixabayImage() }
        emit(Resource.Loading())

        try {
            val remoteResult = api.getPhotos(query = query)
            dao.insertPixabayImage(remoteResult.toPixabayImageEntity())
        } catch (e: Exception) {
            emit(
                Resource.Error(
                    message = e.toString(),
                    data = localResult
                )
            )
        }

        val newResult = dao.getPixabayImages().map { it.toPixabayImage() }
        emit(Resource.Success(newResult))
    }*/
}