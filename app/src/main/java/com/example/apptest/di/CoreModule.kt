package com.example.apptest.di

import com.example.apptest.di.modules.*
import org.koin.core.context.loadKoinModules

object CoreModule {
    private val modules =
        listOf(
            networkModule,
            repositoryModule,
            localModule,
            viewModelModule,
            dataModule,
            utilsModule
        )

    fun init() = loadKoinModules(modules)
}