package com.example.app_21_notetaking.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.app_21_notetaking.repository.NoteRepository
import java.lang.IllegalArgumentException

class NoteViewModelFactory(val app : Application, private val repository: NoteRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return NoteViewModel(app, repository) as T
    }
}