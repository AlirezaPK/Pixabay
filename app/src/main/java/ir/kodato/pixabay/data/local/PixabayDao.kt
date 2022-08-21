package ir.kodato.pixabay.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PixabayDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPixabayImage(pixabayImages: List<PixabayImageEntity>)

    @Query("DELETE FROM PixabayImageEntity")
    suspend fun clearPixabayImage()

    @Query("SELECT * FROM PixabayImageEntity")
    fun getPixabayImages(): Flow<List<PixabayImageEntity>>
}