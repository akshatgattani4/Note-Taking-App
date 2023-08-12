package com.example.app_21_notetaking.repository

import com.example.app_21_notetaking.database.NoteDatabase
import com.example.app_21_notetaking.model.Note

class NoteRepository(val db : NoteDatabase) {
    val dbDAO = db.getDAO()

    suspend fun insertNote(note : Note) = dbDAO.insertNote(note)
    suspend fun deleteNote(note : Note) = dbDAO.deleteNote(note)
    suspend fun updateNote(note : Note) = dbDAO.updateNote(note)

    fun getALlNotes() = dbDAO.getAllNotes()
    fun searchNote(word : String?) = dbDAO.searchNote(word)

}