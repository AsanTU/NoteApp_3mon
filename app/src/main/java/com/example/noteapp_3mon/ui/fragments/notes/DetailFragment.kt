package com.example.noteapp_3mon.ui.fragments.notes

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.noteapp_3mon.App
import com.example.noteapp_3mon.R
import com.example.noteapp_3mon.data.models.NoteModel
import com.example.noteapp_3mon.databinding.FragmentDetailBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.core.graphics.toColorInt

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private var noteId: Int = -1
    private var selectedColor: String = "#FFFFFFFF"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateNote()
        setupListener()
        setupMenuColorsListener()
        setColors()
    }

    private fun updateNote() {
        arguments?.let { args ->
            noteId = args.getInt("noteId", -1)
        }

        if (noteId != -1) {
            val model = App.appDatabase?.noteDao()?.getNoteById(noteId)
            binding.etTitle.setText(model?.title)
            binding.etDescription.setText(model?.description)
        }
    }

    private fun setupListener() = with(binding) {
        tvReady.setOnClickListener {
            val title = etTitle.text.toString()
            val desc = etDescription.text.toString()

            val date = SimpleDateFormat("dd MMMM", Locale("ru")).format(Date())
            val time = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())

            if (noteId != -1) {
                val updateNote = NoteModel(title, desc, date, time, selectedColor)
                updateNote.id = noteId
                App.appDatabase?.noteDao()?.updateNote(updateNote)
            } else {
                App.appDatabase?.noteDao()?.insertNote(NoteModel(title, desc, date, time, selectedColor))
            }
            findNavController().navigateUp()
        }
    }

    private fun setupMenuColorsListener() = with(binding){
        icMenuColors.setOnClickListener {
            colorContainer.visibility = View.VISIBLE
        }
    }

    private fun setColors() = with(binding){
        icYellow.setOnClickListener {
            selectedColor = "#FFEB3B"
            root.setBackgroundColor(selectedColor.toColorInt())
        }
        icPurple.setOnClickListener {
            selectedColor = "#C965D9"
            root.setBackgroundColor(selectedColor.toColorInt())
        }
        icPink.setOnClickListener {
            selectedColor = "#EE9CFF"
            root.setBackgroundColor(selectedColor.toColorInt())
        }
        icRed.setOnClickListener {
            selectedColor = "#F44336"
            root.setBackgroundColor(selectedColor.toColorInt())
        }
        icGreen.setOnClickListener {
            selectedColor = "#4CAF50"
            root.setBackgroundColor(selectedColor.toColorInt())
        }
        icTiffiny.setOnClickListener {
            selectedColor = "#00BCD4"
            root.setBackgroundColor(selectedColor.toColorInt())
        }
    }
}