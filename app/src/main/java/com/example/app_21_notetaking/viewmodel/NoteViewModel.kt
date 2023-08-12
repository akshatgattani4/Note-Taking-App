package com.example.app_21_notetaking.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_21_notetaking.model.Note
import com.example.app_21_notetaking.repository.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(app : Application, private val repository: NoteRepository) : AndroidViewModel(app){

    fun insertNote(note : Note) = viewModelScope.launch {
        repository.insertNote(note)
    }

    fun deleteNote(note : Note) = viewModelScope.launch {
        repository.deleteNote(note)
    }

    fun updateNote(note : Note) = viewModelScope.launch {
        repository.updateNote(note)
    }

    fun getAllNotes() = repository.getALlNotes()
    fun searchNote(word : String?) = repository.searchNote(word)
}