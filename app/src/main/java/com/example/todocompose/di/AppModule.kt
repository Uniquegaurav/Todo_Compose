package com.example.todocompose.di

import android.content.Context
import com.example.todocompose.data.db.NoteDatabase
import com.example.todocompose.data.network.MainAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideApi(): MainAPI {
        return Retrofit.Builder().baseUrl("some url")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(MainAPI::class.java)
    }

    @Provides
    fun provideNoteDatabase(@ApplicationContext context: Context): NoteDatabase {
        return NoteDatabase.invoke(context = context)
    }
}