package com.example.appmangamvvvm.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class MangaDetailsModel(
    var title: String?,
    var genre: String?,
    var author: String?,
    var status: String?,
    var artist: String?,
    var type: String?,
    var imageCover: String?,
    var summary: String?,
    var chaptersList: List<ListChapterModel>?
)

@Parcelize
data class ListChapterModel(
    var titleChapter: String?,
    var releaseDate: String?,
    var linkChapter: String?
) : Parcelable