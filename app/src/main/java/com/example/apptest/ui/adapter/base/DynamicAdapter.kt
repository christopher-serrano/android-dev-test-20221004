package com.example.apptest.ui.adapter.base

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class DynamicAdapter(
    private val typeFactory: BaseTypeFactory,
    items: List<ItemModel> = listOf(),
    private val onClick: (ItemModel, String) -> Unit = { _, _ -> }
) : RecyclerView.Adapter<DynamicAdapterViewHolder<ItemModel>>() {

    var mItems: MutableList<ItemModel> = items.toMutableList()
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
        mItems = data.toMutableList()
        mDiffer?.submitList(mItems)
    }

    override fun getItemViewType(position: Int): Int {
        return mItems[position].type(typeFactory)
    }

    override fun getItemCount() = mItems.count()

    override fun onBindViewHolder(holder: DynamicAdapterViewHolder<ItemModel>, position: Int) {
        holder.bind(mItems[position], position, onClick)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<ItemModel>) {
        mItems.clear()
        mItems.addAll(list)
        notifyDataSetChanged()
    }

    fun insertAtPosition(list: List<ItemModel>, position: Int) {
        mItems.addAll(position, list)
        notifyItemRangeInserted(position, list.size)
        notifyItemChanged(list.size)
    }

    fun removeItems(list: List<ItemModel>) {
        val size = mItems.size
        mItems = list.toMutableList()
        notifyItemRangeRemoved(0, size)
    }

    /**
     * TODO: NOTA - CUANDO SE VAYAN A AGREGAR O REMOVER ITEMS DE UNA LISTA DE ADAPTER DINAMICO
     * TODO: SE TIENE QUE HACER UTILIZANDO LOS METODOS DE ESTA CLASE, Y CONVERTIR LA LISTA DE
     * TODO: ITEMMODEL A MODEL EN LA CLASE QUE LO LLAMA!!!
     */
    @SuppressLint("NotifyDataSetChanged")
    fun removeItem(item: ItemModel) {
        mItems.remove(item)
        notifyDataSetChanged()
    }

    fun insertItem(item: ItemModel) {
        val pos = mItems.size
        mItems.add(item)
        notifyItemInserted(pos)
    }

    fun removeLastItem() {
        val item = mItems.last()
        val position = mItems.indexOf(item)
        mItems.removeLast()
        notifyItemRemoved(position)
    }

    fun clear() {
        val size = mItems.size
        mItems.clear()
        notifyItemRangeRemoved(0, size)
    }
}
