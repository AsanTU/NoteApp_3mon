package com.example.noteapp_3mon

import android.app.Application
import androidx.room.Room
import com.example.noteapp_3mon.data.db.AppDatabase

class App : Application() {

    companion object {
        var appDatabase: AppDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        getInstance()
    }

    private fun getInstance(): AppDatabase? {
        if (appDatabase == null) {
            appDatabase = applicationContext?.let { context ->
                Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "note_database"
                ).fallbackToDestructiveMigration()
                    .allowMainThreadQueries().build()
            }
        }
        return appDatabase
    }
}