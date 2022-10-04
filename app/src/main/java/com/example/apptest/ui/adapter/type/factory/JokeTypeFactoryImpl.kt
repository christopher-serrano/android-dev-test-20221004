package com.example.apptest.ui.adapter.type.factory

import android.view.View
import com.example.apptest.R
import com.example.apptest.data.model.Joke
import com.example.apptest.ui.adapter.base.DynamicAdapterViewHolder
import com.example.apptest.ui.adapter.holder.JokeItemHolder

class JokeTypeFactoryImpl : JokeTypeFactory {
    override fun holder(type: Int, view: View): DynamicAdapterViewHolder<*> {
        return when (type) {
            R.layout.item_joke_category -> JokeItemHolder(view)
            else -> throw RuntimeException("Illegal view type")
        }
    }

    override fun typeJokeItem(type: Joke): Int = R.layout.item_joke_category
}