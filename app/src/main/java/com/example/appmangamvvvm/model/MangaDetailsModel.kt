package com.example.appmangamvvvm.model

import pl.droidsonroids.jspoon.annotation.Selector

data class MangaDetailsModel(
    var title: String?,
    var genre: String?,
    var author: String?,
    var status: String?,
    var artist: String?,
    var type: String?,
    var imageCover: String?,
    var summary: String?,
    var chaptersList: List<ChapterModel>?
)

data class ChapterModel(
    var titleChapter: String?,
    var releaseDate: String?,
    var linkChapter: String?
)