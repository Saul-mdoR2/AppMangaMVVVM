package com.example.appmangamvvvm.presentation

import androidx.lifecycle.ViewModel
import com.example.appmangamvvvm.model.ChapterModel
import com.example.appmangamvvvm.model.MangaDetailsModel

class ChapterItemViewModel : ViewModel(){
    val chapter:ChapterModel? = null

    val titleChapter:String?
    get() =  chapter?.titleChapter

    val releaseDate:String?
        get() =  chapter?.releaseDate

}