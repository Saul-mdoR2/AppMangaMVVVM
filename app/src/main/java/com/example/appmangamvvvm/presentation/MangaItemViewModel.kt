package com.example.appmangamvvvm.presentation

import androidx.lifecycle.ViewModel
import com.example.appmangamvvvm.model.MangaModel

class MangaItemViewModel : ViewModel() {
    var manga: MangaModel? = null

    val mangaTitle: String?
        get() = manga?.title

    val latestChapter: String?
        get() = manga?.latestChapter?.replace(mangaTitle!!, "Chapter")

    val imageUrl: String?
        get() = manga?.imageUrl
}