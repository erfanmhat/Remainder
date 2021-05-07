package ir.erfan_mh_at.android.reminder.db.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import ir.erfan_mh_at.android.reminder.db.entitys.AnyObject

@Dao
interface AnyObjectDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAnyObject(anyObject : AnyObject):Long

    @Delete
    suspend fun deleteAnyObject(anyObject: AnyObject)

    @Query("SELECT * FROM any_objects")
    fun getAllAnyObject(): LiveData<List<AnyObject>>
}