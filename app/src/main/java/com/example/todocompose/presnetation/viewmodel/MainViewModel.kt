package com.example.todocompose.presnetation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todocompose.domain.model.Note
import com.example.todocompose.domain.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {
    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes = _notes.asStateFlow()

    init {
        getAllNotes()
    }

    fun insertNote(text: String) = viewModelScope.launch {
        try {
            repository.insertNote(Note(Random(1000).nextInt().toString(), text, "some"))
        } catch (e: Exception) {
            Log.d("some issue", "some issue")
        }
    }

    fun getAllNotes() = viewModelScope.launch {
        repository.getAllNotes().collectLatest { currentNotes ->
            _notes.value = currentNotes
        }
    }
}