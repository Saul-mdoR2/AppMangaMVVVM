package com.example.appmangamvvvm.di

import com.example.appmangamvvvm.presentation.HomeViewModel
import com.example.appmangamvvvm.presentation.MangaDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel { HomeViewModel(get()) }
}

val mangaDetailsModule = module {
    viewModel { MangaDetailsViewModel(get()) }
}

val presentationModule = listOf(homeModule, mangaDetailsModule)

