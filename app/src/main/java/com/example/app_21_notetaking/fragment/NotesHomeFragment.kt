package com.example.app_21_notetaking.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.app_21_notetaking.MainActivity
import com.example.app_21_notetaking.R
import com.example.app_21_notetaking.adapter.NoteAdapter
import com.example.app_21_notetaking.databinding.FragmentNotesHomeBinding
import com.example.app_21_notetaking.model.Note
import com.example.app_21_notetaking.viewmodel.NoteViewModel

class NotesHomeFragment : Fragment(R.layout.fragment_notes_home), SearchView.OnQueryTextListener {

    private var _binding : FragmentNotesHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var notesViewModel : NoteViewModel
    private lateinit var notesAdapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =  FragmentNotesHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notesViewModel = (activity as MainActivity).noteViewModel

        setUpRecyclerView()

        binding.floatingActionButton.setOnClickListener{
            it.findNavController().navigate(R.id.action_notesHomeFragment_to_newNoteFragment)
        }
    }

    private fun updateUI(note: List<Note>?) {
        if (note != null) {
            if(note.isNotEmpty()){
                binding.cardView.visibility = View.GONE
                binding.recycler1.visibility = View.VISIBLE
            }else{
                binding.cardView.visibility = View.VISIBLE
                binding.recycler1.visibility = View.GONE
            }
        }
    }

    private fun setUpRecyclerView() {
        notesAdapter = NoteAdapter()

        binding.recycler1.apply {
            layoutManager = StaggeredGridLayoutManager(
                2, StaggeredGridLayoutManager.VERTICAL
            )
            setHasFixedSize(true)
            adapter = notesAdapter
        }
        activity?.let {
            notesViewModel.getAllNotes().observe(
                viewLifecycleOwner,{
                    note -> notesAdapter.differ.submitList(note)
                    updateUI(note)
                }
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.home_menu, menu)

        val menuSearch = menu.findItem(R.id.search).actionView as SearchView
        menuSearch.isSubmitButtonEnabled = false
        menuSearch.setOnQueryTextListener(this)

        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        //searchText(query)
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if(newText != null){
            searchText(newText)
        }
        return true
    }

    private fun searchText(query: String?) {
        val s= "%$query"
        notesViewModel.searchNote(s).observe(
            this,
            {list -> notesAdapter.differ.submitList(list)}
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}