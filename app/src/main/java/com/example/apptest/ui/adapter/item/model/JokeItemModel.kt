package com.example.apptest.ui.adapter.item.model

import com.example.apptest.data.model.Joke
import com.example.apptest.ui.adapter.base.BaseTypeFactory
import com.example.apptest.ui.adapter.base.ItemModel
import com.example.apptest.ui.adapter.type.factory.JokeTypeFactory

class JokeItemModel(val model: Joke) : ItemModel() {
    override fun type(typeFactory: BaseTypeFactory): Int {
        return (typeFactory as JokeTypeFactory).typeJokeItem(model)
    }
}