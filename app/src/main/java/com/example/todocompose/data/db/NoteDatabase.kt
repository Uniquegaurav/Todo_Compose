package com.example.todocompose.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todocompose.domain.model.Note
import dagger.hilt.android.qualifiers.ApplicationContext

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun getNoteDao(): NoteDao

    companion object {
        private var instance: NoteDatabase? = null

        operator fun invoke(context: Context) = instance ?: synchronized(Any()) {
            instance ?: Room.databaseBuilder(
                context.applicationContext,
                NoteDatabase::class.java,
                "note_db.db"
            ).build()
        }
    }
}