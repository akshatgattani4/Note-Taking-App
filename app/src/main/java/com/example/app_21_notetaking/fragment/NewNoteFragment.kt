package com.example.app_21_notetaking.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.app_21_notetaking.MainActivity
import com.example.app_21_notetaking.R
import com.example.app_21_notetaking.adapter.NoteAdapter
import com.example.app_21_notetaking.databinding.FragmentNewNoteBinding
import com.example.app_21_notetaking.model.Note
import com.example.app_21_notetaking.viewmodel.NoteViewModel

class NewNoteFragment : Fragment(R.layout.fragment_new_note) {

    private var _binding: FragmentNewNoteBinding? = null
    private val binding get() = _binding!!

    private lateinit var notesViewModel: NoteViewModel
    private lateinit var notesAdapter: NoteAdapter
    private lateinit var myView : View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNewNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notesViewModel = (activity as MainActivity).noteViewModel
        myView = view
    }

    private fun saveNote(view : View){
        val noteTitle = binding.edtNoteTitle.text.toString().trim()
        val noteBody = binding.edtNoteBody.text.toString().trim()

        if(noteTitle.isNotEmpty()) {
            val note = Note(0, noteTitle, noteBody)
            notesViewModel.insertNote(note)

            Toast.makeText(
                myView.context,
                "Note Saved Successfully", Toast.LENGTH_SHORT
            ).show()

            view.findNavController().navigate(R.id.action_newNoteFragment_to_notesHomeFragment)
        }else{
            Toast.makeText(
                myView.context,
                "Please enter a Title", Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.new_note_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.save -> {
                saveNote(myView)
            }
        }

        return super.onOptionsItemSelected(item)
    }
}