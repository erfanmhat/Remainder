package ir.erfan_mh_at.android.reminder.app

import android.app.Application
import android.content.Context

class ReminderApplication : Application() {
    companion object{
        lateinit var context : Context
    }

    override fun onCreate() {
        super.onCreate()
        context=this
    }
}