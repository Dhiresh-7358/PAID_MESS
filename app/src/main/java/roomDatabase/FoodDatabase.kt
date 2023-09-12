package roomDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [FoodItemsModel::class, Cart::class, Like::class],
    version = 1,
    exportSchema = false
)
abstract class FoodDatabase : RoomDatabase() {

    abstract fun foodDao(): FoodDao
    abstract fun cartDao(): CartDao
    abstract fun likeDao(): LikeDao


    companion object {
        @Volatile
        private var instance: FoodDatabase? = null

        fun getDatabase(context: Context) = instance ?: synchronized(this) {
            Room.databaseBuilder(
                context.applicationContext,
                FoodDatabase::class.java,
                name = "food_database"
            ).build().also { instance = it }
        }

    }

}