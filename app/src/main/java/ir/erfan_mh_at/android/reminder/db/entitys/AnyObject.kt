package ir.erfan_mh_at.android.reminder.db.entitys

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
    tableName = "any_objects"
)
data class AnyObject(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
//    @ForeignKey() TODO
    val anyObject_id: Int? = null,
    val name: String,
    val type: Int,//###
    val color: String?,//###
    val image: String?,//###
    val data: String,
    val updated_at: String?,//###
    val created_at: String?//###
) : Serializable