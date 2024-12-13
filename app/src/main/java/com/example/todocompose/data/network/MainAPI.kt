package com.example.todocompose.data.network

import com.example.todocompose.domain.model.Data
import retrofit2.Response
import retrofit2.http.GET

interface MainAPI {
    @GET("endpoint")
    suspend fun fetchData(): Response<Data>
}