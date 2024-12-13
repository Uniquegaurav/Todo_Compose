package com.example.todocompose.presnetation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.example.todocompose.presnetation.viewmodel.MainViewModel

@Composable
fun MainComponent(viewModel: MainViewModel) {
    Column {
        ShowNoteComponent(viewModel = viewModel)
        AddNoteComponent(viewModel = viewModel)
    }
}