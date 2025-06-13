package com.example.noteapp_3mon.ui.fragments.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp_3mon.App
import com.example.noteapp_3mon.R
import com.example.noteapp_3mon.data.models.NoteModel
import com.example.noteapp_3mon.databinding.FragmentNotesBinding
import com.example.noteapp_3mon.ui.adapters.NoteAdapter
import com.example.noteapp_3mon.utils.OnClickItem

class NotesFragment : Fragment(), OnClickItem {

    private lateinit var binding: FragmentNotesBinding
    private val noteAdapter = NoteAdapter(this, this)
    private var isGrid = false
    private lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListener()
        getData()
        setupGridMenu()
    }

    private fun initialize() {
        binding.rvNotes.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = noteAdapter
        }
    }

    private fun setupListener() {
        binding.btnAction.setOnClickListener {
            findNavController().navigate(R.id.action_notesFragment_to_detailFragment)
        }
    }

    private fun getData() {
        App.appDatabase?.noteDao()?.getAll()?.observe(viewLifecycleOwner) { listModel ->
            noteAdapter.submitList(listModel)
        }
    }

    override fun onLongClick(noteModel: NoteModel) {
        val builder = AlertDialog.Builder(requireContext())

        with(builder) {
            setTitle("Удалить заметку?")
            setPositiveButton("Delete"){_, _ ->
                App.appDatabase?.noteDao()?.deleteNote(noteModel)
            }
            setNegativeButton("Cancel"){dialog,_ ->
                dialog.cancel()
            }
            show()
        }
        builder.create()
    }

    override fun onClick(noteModel: NoteModel) {
        val action = NotesFragmentDirections.actionNotesFragmentToDetailFragment(noteModel.id)
        findNavController().navigate(action)
    }

    private fun setupGridMenu(){
        binding.ivGridMenu.setOnClickListener {
            isGrid = !isGrid
            layoutManager = if (isGrid) GridLayoutManager(requireContext(), 2)
            else LinearLayoutManager(requireContext())
            binding.rvNotes.layoutManager = layoutManager
            binding.rvNotes.adapter = noteAdapter

            val iconRes = if (isGrid) R.drawable.ic_launcher_background else R.drawable.ic_launcher_foreground
            binding.ivGridMenu.setImageResource(iconRes)
        }
    }
}