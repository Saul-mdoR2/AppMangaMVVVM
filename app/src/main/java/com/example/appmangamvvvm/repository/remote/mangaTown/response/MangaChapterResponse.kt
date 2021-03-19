package com.example.appmangamvvvm.repository.remote.mangaTown.response

import pl.droidsonroids.jspoon.annotation.Selector

class MangaChapterResponse {
    @Selector(value = "#viewer>a>img", attr = "src")
    var currentPageLink: String? = ""

    @Selector(value = ".prew_page", attr = "href")
    var previousPage: String? = ""

    @Selector(value = ".next_page", attr = "href")
    var nextPage: String? = ""

    @Selector(".go_page>.page_select>select>option[selected]")
    var currentPage: String? = ""

    @Selector(".go_page>.page_select>select>option")
    var listPages: List<PagesResponse>? = listOf()
}

class PagesResponse {
    @Selector("option")
    var numPage: String? = ""

    @Selector(value = "option", attr = "value")
    var linkPage: String? = ""
}