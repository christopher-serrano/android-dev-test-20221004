package com.example.apptest.ui.adapter.base

import android.view.View

interface BaseTypeFactory {
    fun holder(type: Int, view: View): DynamicAdapterViewHolder<*>
}
