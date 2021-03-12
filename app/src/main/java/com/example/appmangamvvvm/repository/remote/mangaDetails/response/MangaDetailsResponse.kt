package com.example.appmangamvvvm.repository.remote.mangaDetails.response

import pl.droidsonroids.jspoon.annotation.Selector

class MangaDetailsResponse {

    @Selector(".title-top")
    var title:String? = null

    @Selector(".detail_info>ul>li:eq(4)")
    var genre: String? = null

    @Selector(".detail_info>ul>li:eq(5)")
    var author: String? = null

    @Selector(".detail_info>ul>li:eq(7)")
    var status: String? = null

    @Selector(".detail_info>ul>li:eq(6)")
    var artist: String? = null

    @Selector(".detail_info>ul>li:eq(9)>a")
    var type: String? = null

    @Selector(value = ".detail_info>img", attr = "src")
    var image: String? = null

    @Selector("#show")
    var summary: String? = null

    @Selector(".chapter_list>li")
    var listaCapitulos: List<ChapterResponse> = listOf()
}


class ChapterResponse {
    @Selector("a")
    var titleChapter: String? = null

    @Selector(".time")
    var releaseDate: String? = null

    @Selector(value = "a", attr = "href")
    var linkChapter: String? = null

}