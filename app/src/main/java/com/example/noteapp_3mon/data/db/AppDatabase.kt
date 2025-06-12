package com.example.noteapp_3mon.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.noteapp_3mon.data.models.NoteModel

@Database(entities = [NoteModel::class], version = 2)
abstract class AppDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDao
}