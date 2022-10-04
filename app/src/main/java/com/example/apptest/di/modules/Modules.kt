package com.example.apptest.di.modules

import android.content.Context
import com.example.apptest.network.ApiClient
import com.example.apptest.repository.JokeRepository
import com.example.apptest.repository.JokeRepositoryImpl
import com.example.apptest.ui.viewmodel.JokeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val networkModule = module {
    single { ApiClient.invoke() }
}

val repositoryModule = module {
    factory<JokeRepository> { JokeRepositoryImpl() }
}

val localModule = module {
}

val dataModule = module {
    single { androidContext().getSharedPreferences("Prefs", Context.MODE_PRIVATE) }
}

val viewModelModule = module {
    viewModel { JokeViewModel(get()) }
}

val utilsModule = module {

}
