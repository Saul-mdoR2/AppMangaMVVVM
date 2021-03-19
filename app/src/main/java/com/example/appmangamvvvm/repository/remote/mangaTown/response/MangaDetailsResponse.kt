package com.example.appmangamvvvm.repository.remote.mangaTown.response

import pl.droidsonroids.jspoon.annotation.Selector

class MangaDetailsResponse {

    @Selector(".title-top")
    var title: String? = ""

    @Selector(".detail_info>ul>li:eq(4)")
    var genre: String? = ""

    @Selector(".detail_info>ul>li:eq(5)")
    var author: String? = ""

    @Selector(".detail_info>ul>li:eq(7)")
    var status: String? = ""

    @Selector(".detail_info>ul>li:eq(6)")
    var artist: String? = ""

    @Selector(".detail_info>ul>li:eq(9)>a")
    var type: String? = ""

    @Selector(value = ".detail_info>img", attr = "src")
    var imageCover: String? = ""

    @Selector("#show")
    var summary: String? = ""

    @Selector(".chapter_list>li")
    var chaptersList: List<ChapterResponse> = listOf()
}


class ChapterResponse {
    @Selector("a")
    var titleChapter: String? = ""

    @Selector(".time")
    var releaseDate: String? = ""

    @Selector(value = "a", attr = "href")
    var linkChapter: String? = ""

}