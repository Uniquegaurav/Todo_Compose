package com.example.todocompose.domain.repository

import com.example.todocompose.data.db.NoteDatabase
import com.example.todocompose.domain.model.Note
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepository @Inject constructor(private val db: NoteDatabase) {

    fun getAllNotes(): Flow<List<Note>> {
        return db.getNoteDao().getAllNotes()
    }

    suspend fun insertNote(note: Note) {
        db.getNoteDao().insertNote(note)
    }

    suspend fun deleteNote(note: Note) {
        db.getNoteDao().deleteNote(note)
    }
}