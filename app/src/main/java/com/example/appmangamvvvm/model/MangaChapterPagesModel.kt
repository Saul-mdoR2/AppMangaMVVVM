package com.example.appmangamvvvm.model

class MangaChapterPagesModel(
    var currentPageLink: String?,
    var previousPage: String?,
    var nextPage: String?,
    var currentPage: String?,
    var listPages: List<PagesModel>?
)

class PagesModel(
    var numPage: String?,
    var linkPage: String?
)