package com.example.apptest.ui.adapter.item.model

import com.example.apptest.ui.adapter.base.BaseTypeFactory
import com.example.apptest.ui.adapter.base.ItemModel
import com.example.apptest.ui.adapter.type.factory.JokeTypeFactory

class JokeTitleItemModel(val model: String): ItemModel() {
    override fun type(typeFactory: BaseTypeFactory): Int {
        return (typeFactory as JokeTypeFactory).typeJokeListItem(model)
    }
}