package com.example.apptest.ui.adapter.type.factory

import com.example.apptest.data.model.Joke
import com.example.apptest.ui.adapter.base.BaseTypeFactory

interface JokeTypeFactory: BaseTypeFactory {
    fun typeJokeItem(type: Joke): Int
}