package com.example.noteapp_3mon.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.noteapp_3mon.data.models.NoteModel

class DiffCallback : DiffUtil.ItemCallback<NoteModel>() {
    override fun areItemsTheSame(
        oldItem: NoteModel,
        newItem: NoteModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: NoteModel,
        newItem: NoteModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

}