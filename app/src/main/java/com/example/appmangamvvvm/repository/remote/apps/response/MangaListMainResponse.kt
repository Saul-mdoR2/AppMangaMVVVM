package com.example.appmangamvvvm.repository.remote.apps.response

import pl.droidsonroids.jspoon.annotation.Selector

data class MangaListMainResponse (
    @Selector(".manga_pic_list>li") var listMangas:List<MangaMainResponse>
)