package com.example.noteapp_3mon.utils

import com.example.noteapp_3mon.data.models.NoteModel

interface OnClickItem {

    fun onLongClick(noteModel: NoteModel)

    fun onClick(noteModel: NoteModel)
}