package roomDatabase

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(foodItemDB: MutableList<FoodItemsModel>)

    @Query("UPDATE foodItemDB SET isLiked = :like WHERE id = :ID ")
    suspend fun updateLike(like:Boolean,ID:Long)

    @Query("UPDATE foodItemDB SET inCart = :cart WHERE id =:ID")
    suspend fun updateCart(cart: Boolean,ID: Long)


    @Query("SELECT * FROM foodItemDB")
    fun getFoodItems(): LiveData<MutableList<FoodItemsModel>>

    @Query("DELETE FROM foodItemDB")
    suspend fun deleteAll();

    @Query("SELECT * FROM foodItemDB WHERE id LIKE :idd || '%' ")
    fun fetFoodCategory(idd: Int): LiveData<MutableList<FoodItemsModel>>

}