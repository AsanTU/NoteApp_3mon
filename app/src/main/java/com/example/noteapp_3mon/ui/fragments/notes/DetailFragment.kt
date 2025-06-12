package com.example.noteapp_3mon.ui.fragments.notes

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

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()
    }

    private fun setupListener() = with(binding) {
        tvReady.setOnClickListener {
            val title = etTitle.text.toString()
            val desc = etDescription.text.toString()

            val date = SimpleDateFormat("dd MMMM", Locale("ru")).format(Date())
            val time = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
            App.appDatabase?.noteDao()?.insertNote(NoteModel(title, desc, date, time))
            findNavController().navigateUp()
        }
    }
}