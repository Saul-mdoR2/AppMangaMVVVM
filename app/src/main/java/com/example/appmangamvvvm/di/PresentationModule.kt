package com.example.appmangamvvvm.di

import com.example.appmangamvvvm.presentation.mainLatestMangas.HomeViewModel
import com.example.appmangamvvvm.presentation.mangaChapterViewer.ViewerViewModel
import com.example.appmangamvvvm.presentation.mangaDetails.MangaDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel {
        HomeViewModel(
            get()
        )
    }
}

val mangaDetailsModule = module {
    viewModel {
        MangaDetailsViewModel(
            get()
        )
    }
}

val mangaChapterViewerModule = module {
    viewModel {
        ViewerViewModel(
            get()
        )
    }
}

val presentationModule = listOf(homeModule, mangaDetailsModule, mangaChapterViewerModule)

