package roomDatabase

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "food")
data class Food(
    @PrimaryKey val id: Long,
    val foodName: String,
    val foodPrice: Int,
    val isLiked:Boolean,
    val quantity:Int,
    val about:String,
    val isVeg:Boolean,
    val foodImage: Int,
    val foodLike: Boolean
)

//data class Liked(
//    @PrimaryKey(autoGenerate = true) val id: Long,
//    val foodName: String,
//    val foodPrice: Int,
//    val foodType: String,
//    val foodImage: Int,
//    val foodLike: Boolean
//)

//data class Cart(
//    @PrimaryKey(autoGenerate = true) val id: Long,
//    val foodName:String,
//    val foodPrice:Int,
//    val foodLike:Boolean
//)

