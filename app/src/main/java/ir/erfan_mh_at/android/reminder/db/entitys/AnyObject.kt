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
    var anyObject_id: Int? = null,
    var name: String,
    var type: Int,//###
    var color: String?,//###
    var image: String?,//###
    var data: String,
    var updated_at: String?,//###
    var created_at: String?//###
) : Serializable