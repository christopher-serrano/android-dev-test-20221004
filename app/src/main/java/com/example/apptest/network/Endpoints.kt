package com.example.apptest.network

import com.example.apptest.data.model.Joke
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Endpoints {

    @GET("/jokes/categories")
    suspend fun getCategories(): Response<List<String>?>

    @GET("/jokes/random?category={category}")
    suspend fun getRandomJokeByCategory(
        @Query(value = "category") category: String
    ): Response<Joke?>

}