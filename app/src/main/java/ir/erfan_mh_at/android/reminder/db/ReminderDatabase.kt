package ir.erfan_mh_at.android.reminder.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ir.erfan_mh_at.android.reminder.db.daos.AnyObjectDao
import ir.erfan_mh_at.android.reminder.db.entitys.AnyObject

@Database(
    entities = [
        AnyObject::class
    ],
    version = 1
)
abstract class ReminderDatabase : RoomDatabase() {

    abstract fun getAnyObjectDao(): AnyObjectDao

    companion object {
        @Volatile
        private var instance: ReminderDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createReminderDataBase(context).also { instance = it }
        }

        private fun createReminderDataBase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ReminderDatabase::class.java,
                "reminder_db.db"
            ).build()
    }
}