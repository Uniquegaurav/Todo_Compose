package com.example.todocompose.presnetation.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.todocompose.domain.model.Note
import com.example.todocompose.presnetation.viewmodel.MainViewModel
import com.example.todocompose.presnetation.viewmodel.Resource

@Composable
fun ShowNoteComponent(viewModel: MainViewModel) {
    val notesResource by viewModel.notes.collectAsState()

    when (notesResource) {
        is Resource.Loading -> {
            Text(text = "loading")
        }
        is Resource.Success -> {
            val notes = (notesResource as Resource.Success<List<Note>>).data
            LazyColumn {
                items(notes) { note ->
                    Text(text = note.noteTitle)
                }
            }
        }
        is Resource.Error -> {
            val errorMessage = (notesResource as Resource.Error).message
            Text(text = "Error: $errorMessage")
        }
    }
}

