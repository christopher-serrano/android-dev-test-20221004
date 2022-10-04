package com.example.apptest.ui.adapter.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class DynamicDiffAdapter(
    private val typeFactory: BaseTypeFactory,
    private val onClick: (ItemModel, String) -> Unit = { _, _ -> }
) : RecyclerView.Adapter<DynamicAdapterViewHolder<ItemModel>>() {

    val mItems
        get() = mDiffer?.currentList.orEmpty()

    private var mDiffer: AsyncListDiffer<ItemModel>? = null
    private var mDiffCallback: DiffUtil.ItemCallback<ItemModel>? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        p1: Int
    ): DynamicAdapterViewHolder<ItemModel> {
        val view = LayoutInflater.from(parent.context).inflate(p1, parent, false)
        return typeFactory.holder(p1, view) as DynamicAdapterViewHolder<ItemModel>
    }

    fun setCallback(callback: DiffUtil.ItemCallback<ItemModel>) {
        mDiffCallback = callback
        mDiffer = AsyncListDiffer(this, mDiffCallback!!)
    }

    fun submitList(data: List<ItemModel>) {
        mDiffer?.submitList(data.toList())
    }

    override fun getItemViewType(position: Int): Int {
        return mItems[position].type(typeFactory)
    }

    override fun getItemCount() = mItems.size

    override fun onBindViewHolder(holder: DynamicAdapterViewHolder<ItemModel>, position: Int) {
        holder.bind(mItems[position], position, onClick)
    }
}
