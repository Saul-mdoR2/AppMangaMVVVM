package com.example.appmangamvvvm.model

import pl.droidsonroids.jspoon.annotation.Selector

data class MangaDetailsModel(
    // Selector(".title-top")
    var title:String?,
    var genre: String?,
    var author: String?,
    var status: String?,
    var artist: String?,
    var type: String?,
    var imageCover: String?,
    var summary: String?,
    var chaptersList: ArrayList<ChapterModel>?
)

data class ChapterModel(
    var titleChapter: String?,
    var releaseDate: String?,
    var linkChapter: String?
)