package com.example.apptest.ui.adapter.base

import com.example.apptest.ui.adapter.base.BaseTypeFactory

abstract class ItemModel {
    abstract fun type(typeFactory: BaseTypeFactory): Int
}
