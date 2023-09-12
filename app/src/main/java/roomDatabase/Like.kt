package roomDatabase

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "like")
data class Like(
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
