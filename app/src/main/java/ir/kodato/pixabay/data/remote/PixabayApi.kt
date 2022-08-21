package ir.kodato.pixabay.data.remote

import ir.kodato.pixabay.BuildConfig
import ir.kodato.pixabay.data.remote.dto.ResultDto
import ir.kodato.pixabay.util.Constant.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {

    @GET("api")
    suspend fun getPhotos(
        @Query("key") apiKey: String = BuildConfig.API_KEY,
        @Query("q") query: String,
        @Query("orientation") orientation: String = "vertical",
        @Query("image_type") imageType: String = "photo",
    ): ResultDto
}