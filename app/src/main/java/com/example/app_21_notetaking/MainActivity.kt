package com.example.app_21_notetaking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.app_21_notetaking.database.NoteDatabase
import com.example.app_21_notetaking.databinding.ActivityMainBinding
import com.example.app_21_notetaking.repository.NoteRepository
import com.example.app_21_notetaking.viewmodel.NoteViewModel
import com.example.app_21_notetaking.viewmodel.NoteViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var noteViewModel : NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViewModel()
    }

    private fun setUpViewModel() {
        val repository = NoteRepository(NoteDatabase(this))
        val factory = NoteViewModelFactory(application, repository)
        noteViewModel = ViewModelProvider(this, factory).get(NoteViewModel::class.java)
    }
}