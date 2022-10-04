package com.example.apptest.ui.adapter.holder

import android.view.View
import com.example.apptest.databinding.ItemJokeCategoryBinding
import com.example.apptest.ui.adapter.base.DynamicAdapterViewHolder
import com.example.apptest.ui.adapter.base.ItemModel
import com.example.apptest.ui.adapter.item.model.JokeItemModel

class JokeItemHolder(view: View) :
    DynamicAdapterViewHolder<JokeItemModel>(view) {

    private var binding = ItemJokeCategoryBinding.bind(itemView)
    lateinit var onClick: (item: ItemModel, action: String) -> Unit

    override fun bind(
        item: JokeItemModel,
        position: Int,
        onClick: (ItemModel, String) -> Unit
    ) {
        this.onClick = onClick

        binding.tvJokeCategory.text = item.model.categories.toString()
        binding.tvJokeContent.text = item.model.value
    }
}