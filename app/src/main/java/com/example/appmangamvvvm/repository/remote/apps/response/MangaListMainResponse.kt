package com.example.appmangamvvvm.repository.remote.apps.response

import pl.droidsonroids.jspoon.annotation.Selector

class MangaListMainResponse {
    @Selector(".manga_pic_list>li")
    var listMangas: List<MangaMainResponse> = listOf()
}