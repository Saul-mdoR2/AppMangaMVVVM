package com.example.appmangamvvvm.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import pl.droidsonroids.jspoon.annotation.Selector

@Parcelize
data class MangaMainModel (
    var image: String?,
    var title: String?,
    var latestChapter: String?,
   var enlaceDetalles: String?
):Parcelable