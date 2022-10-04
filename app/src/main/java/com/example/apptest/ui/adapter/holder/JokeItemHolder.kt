package com.example.apptest.ui.adapter.holder

import android.view.View
import com.example.apptest.databinding.ItemJokeCategoryBinding
import com.example.apptest.ui.adapter.base.DynamicAdapterViewHolder
import com.example.apptest.ui.adapter.base.ItemModel
import com.example.apptest.ui.adapter.item.model.JokeItemModel
import com.example.apptest.ui.adapter.item.model.JokeTitleItemModel
import com.example.apptest.utils.setOneOffClickListener

class JokeItemHolder(view: View) :
    DynamicAdapterViewHolder<JokeTitleItemModel>(view) {

    private var binding = ItemJokeCategoryBinding.bind(itemView)
    lateinit var onClick: (item: ItemModel, action: String) -> Unit

    override fun bind(
        item: JokeTitleItemModel,
        position: Int,
        onClick: (ItemModel, String) -> Unit
    ) {
        this.onClick = onClick

        binding.tvJokeCategory.text = item.model

        binding.btnGetRandomJoke.setOneOffClickListener {
            onClick.invoke(item, "ACTION_GOTO")
        }
    }
}