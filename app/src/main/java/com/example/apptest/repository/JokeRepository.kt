package com.example.apptest.repository

import com.example.apptest.data.model.Joke

interface JokeRepository {

    suspend fun getJokeCategories(): List<String>?
    suspend fun getJokeByCategory(category: String): Joke?

}