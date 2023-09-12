package roomDatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")
data class Cart(
    @PrimaryKey val id: Long,
    val foodName: String,
    val foodPrice: Int,
    val quantity: Int,
    val isVeg: Boolean,
)
