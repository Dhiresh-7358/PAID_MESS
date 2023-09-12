package roomDatabase

import android.os.FileObserver.DELETE
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.room.*
import dataAndAdapter.CartItemsModel


@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insetItem(item: Cart)

    @Query("UPDATE cart SET quantity =:quan WHERE id=:ID")
    suspend fun updateQuantity(ID:Long,quan:Int)

    @Query("SELECT * FROM cart")
    fun fetchAllItems():LiveData<MutableList<Cart>>

    @Query("DELETE from cart where id=:ID" )
    suspend fun deleteItem(ID: Long)


}