package com.example.apptest

import android.app.Application
import com.example.apptest.di.CoreModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin

class AppTestApplication: Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()
        initKoin()
        CoreModule.init()
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@AppTestApplication)
        }
    }
}