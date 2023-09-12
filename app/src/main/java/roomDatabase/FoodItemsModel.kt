package roomDatabase

import android.content.Context
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Room
import java.io.Serializable
import java.util.Objects


@Entity(tableName = "foodItemDB")
data class FoodItemsModel(
    @PrimaryKey val id: Long,
    val foodName: String,
    val foodPrice: Int,
    val isLiked: Boolean,
    val inCart: Boolean,
    val quantity: Int,
    val about: String,
    val isVeg: Boolean,
    val foodImage: Int,

    ) : Serializable
