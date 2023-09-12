package roomDatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface LikeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertLikedItems(likeItem: Like)

    @Query("SELECT * FROM like")
    fun getLikeItems(): LiveData<MutableList<FoodItemsModel>>

    @Query("DELETE FROM like WHERE id= :idd")
    suspend fun deleteLikeItem(idd:Long);

}