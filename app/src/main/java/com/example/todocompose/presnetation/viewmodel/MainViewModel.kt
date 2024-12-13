package com.example.todocompose.presnetation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todocompose.domain.model.Note
import com.example.todocompose.domain.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

sealed class Resource<T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Error<T>(val message: String, val cause: Throwable? = null) : Resource<T>()
    data class Loading<T>(val isLoading: Boolean = true) : Resource<T>()
}

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

    private val _notes = MutableStateFlow<Resource<List<Note>>>(Resource.Loading())
    val notes: StateFlow<Resource<List<Note>>> = _notes.asStateFlow()

    init {
        getAllNotes()
    }

    fun insertNote(text: String) = viewModelScope.launch {
        try {
            val newNote = Note(
                noteId = UUID.randomUUID().toString(),
                noteTitle = text,
                noteContent = ""
            )
            repository.insertNote(newNote)
        } catch (e: Exception) {
            Log.e("InsertNoteError", "Failed to insert note", e)
        }
    }

    private fun getAllNotes() = viewModelScope.launch {
        repository.getAllNotes()
            .onStart {
                _notes.value = Resource.Loading(true)
            }
            .catch { e ->
                _notes.value = Resource.Error("Error fetching notes", e)
            }
            .collectLatest { currentNotes ->
                _notes.value = Resource.Success(currentNotes)
            }
    }
}
