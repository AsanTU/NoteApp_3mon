package com.example.noteapp_3mon.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.noteapp_3mon.data.models.NoteModel

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(noteModel: NoteModel)

    @Query("SELECT * FROM note")
    fun getAll(): LiveData<List<NoteModel>>

    @Delete
    fun deleteNote(noteModel: NoteModel)

    @Update
    fun updateNote(noteModel: NoteModel)

    @Query("SELECT * FROM note WHERE id = :id")
    fun getNoteById(id: Int): NoteModel?
}