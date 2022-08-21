package ir.kodato.pixabay.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.kodato.pixabay.data.local.PixabayDatabase
import ir.kodato.pixabay.data.remote.PixabayApi
import ir.kodato.pixabay.data.repository.PixabayRepositoryImpl
import ir.kodato.pixabay.domain.repository.PixabayRepository
import ir.kodato.pixabay.util.Constant.BASE_URL
import ir.kodato.pixabay.util.Constant.DATABASE_NAME
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApi(): PixabayApi {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideDatabase(app: Application): PixabayDatabase {
        return Room.databaseBuilder(
            app,
            PixabayDatabase::class.java,
            DATABASE_NAME,
        ).build()
    }

    @Provides
    @Singleton
    fun provideRepository(
        api: PixabayApi,
        db: PixabayDatabase
    ): PixabayRepository {
        return PixabayRepositoryImpl(api, db)
    }
}