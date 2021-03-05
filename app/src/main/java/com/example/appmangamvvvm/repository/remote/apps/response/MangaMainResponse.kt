package com.example.appmangamvvvm.repository.remote.apps.response

import pl.droidsonroids.jspoon.annotation.Selector

data class MangaMainResponse (
    @Selector(value = ".manga_cover>img", attr = "src") var image: String?,
    @Selector(".title>a") var title: String?,
    @Selector(".score>b") var score:Float?,
    @Selector(".keyWord>a") var genre:String?,
    @Selector(".new_chapter>a") var latestChapter: String?,
    @Selector(value = ".title>a", attr = "href") var enlaceDetalles: String?
)