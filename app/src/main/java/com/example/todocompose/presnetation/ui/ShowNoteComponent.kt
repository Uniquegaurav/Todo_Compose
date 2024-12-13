package com.example.todocompose.presnetation.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.todocompose.presnetation.viewmodel.MainViewModel

@Composable
fun ShowNoteComponent(viewModel: MainViewModel) {
    val notes by viewModel.notes.collectAsState()

    LazyColumn {
        items(notes) {
            Text(text = it.noteTitle)
        }
    }
}


