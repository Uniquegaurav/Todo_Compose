package com.example.todocompose.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("notes")
data class Note(@PrimaryKey val noteId: String, val noteTitle: String, val noteContent: String)
