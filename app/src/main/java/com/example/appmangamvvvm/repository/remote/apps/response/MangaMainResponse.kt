package com.example.appmangamvvvm.repository.remote.apps.response

import pl.droidsonroids.jspoon.annotation.Selector

class MangaListMainResponse {
    @Selector(".manga_pic_list>li")
    var listMangas: List<MangaMainResponse> = listOf()
}

class MangaMainResponse {
    @Selector(value = ".manga_cover>img", attr = "src")
    var imageUrl: String? = ""

    @Selector(".title>a")
    var title: String? = ""

    @Selector(".new_chapter>a")
    var latestChapter: String? = ""

    @Selector(value = ".title>a", attr = "href")
    var detailsUrl: String? = ""
}

