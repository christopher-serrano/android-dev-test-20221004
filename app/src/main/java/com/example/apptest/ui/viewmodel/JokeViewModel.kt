package com.example.apptest.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.apptest.data.model.Joke
import com.example.apptest.repository.JokeRepository
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class JokeViewModel(context: Context): BaseViewModel(), KoinComponent {

    private val jokeRepository: JokeRepository by inject()

    var jokeObj = MutableLiveData<Joke?>()
    var categoryListObj = MutableLiveData<List<String>?>()

    fun getCategoryList() {
        viewModelScope.launch {
            getCategoryListAsync()
        }
    }

    private suspend fun getCategoryListAsync() {
        val result = kotlin.runCatching {
            jokeRepository.getJokeCategories()
        }
        with(result) {
            onSuccess {
                categoryListObj.postValue(it)
            }
            onFailure {
                onError.postValue(it.message)
            }
        }
    }

    fun getJokeByCategory(cat: String) {
        viewModelScope.launch {
            getJokeByCategoryAsync(cat)
        }
    }

    private suspend fun getJokeByCategoryAsync(cat: String) {
        val result = kotlin.runCatching {
            jokeRepository.getJokeByCategory(cat)
        }
        with(result) {
            onSuccess {
                jokeObj.postValue(it)
            }
            onFailure {
                onError.postValue(it.message)
            }
        }
    }

}