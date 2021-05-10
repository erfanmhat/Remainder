package ir.erfan_mh_at.android.reminder.repositorys

import ir.erfan_mh_at.android.reminder.db.ReminderDatabase
import ir.erfan_mh_at.android.reminder.db.entitys.AnyObject

class AnyObjectRepository(
    private val db: ReminderDatabase
) {
    suspend fun upsertAnyObject(item: AnyObject) = db.getAnyObjectDao().upsertAnyObject(item)

    suspend fun deleteAnyObject(item: AnyObject) = db.getAnyObjectDao().deleteAnyObject(item)

    fun getAllAnyObject() = db.getAnyObjectDao().getAllAnyObject()

    fun getAllAnyObjectWithParentId(anyObject_id: Int?) =
        db.getAnyObjectDao().getAllAnyObjectWithParentId(anyObject_id)
}