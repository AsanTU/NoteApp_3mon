package com.example.noteapp_3mon.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp_3mon.data.models.NoteModel
import com.example.noteapp_3mon.databinding.ItemNoteBinding
import com.example.noteapp_3mon.utils.DiffCallback
import com.example.noteapp_3mon.utils.OnClickItem

class NoteAdapter(
    private val onLongClick: OnClickItem,
    private val onClick: OnClickItem
) : ListAdapter<NoteModel, NoteAdapter.ViewHolder>(DiffCallback()) {

    class ViewHolder(private val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NoteModel) {
            binding.tvTitle.text = item.title
            binding.tvDesc.text = item.description
            binding.tvDate.text = item.date
            binding.tvTime.text = item.time
            binding.root.setBackgroundColor(item.color.toColorInt())
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            ItemNoteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))

        holder.itemView.setOnLongClickListener {
            onLongClick.onLongClick(getItem(position))
            true
        }

        holder.itemView.setOnClickListener {
            onClick.onClick(getItem(position))
        }
    }
}