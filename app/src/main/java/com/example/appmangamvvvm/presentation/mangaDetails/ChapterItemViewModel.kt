package com.example.appmangamvvvm.presentation.mangaDetails

import androidx.lifecycle.ViewModel
import com.example.appmangamvvvm.model.ListChapterModel

class ChapterItemViewModel : ViewModel() {
    var chapter: ListChapterModel? = null

    val titleChapter: String?
        get() = chapter?.titleChapter

    val releaseDate: String?
        get() = chapter?.releaseDate

}