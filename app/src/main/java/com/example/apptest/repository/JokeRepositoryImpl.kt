package com.example.apptest.repository

import com.example.apptest.data.model.Joke
import com.example.apptest.network.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent

class JokeRepositoryImpl : JokeRepository, KoinComponent {

    private val api = ApiClient.invoke()

    override suspend fun getJokeCategories(): List<String>? = withContext(Dispatchers.IO) {
        val result = api.getCategories()
        result.body()
    }

    override suspend fun getJokeByCategory(category: String): Joke? = withContext(Dispatchers.IO) {
        val result = api.getRandomJokeByCategory(category)
        result.body()
    }
}