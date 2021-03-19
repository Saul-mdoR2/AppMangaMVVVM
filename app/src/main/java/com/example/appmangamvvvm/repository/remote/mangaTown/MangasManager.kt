package com.example.appmangamvvvm.repository.remote.mangaTown

import com.example.appmangamvvvm.repository.remote.mangaTown.service.MangaService
import com.example.appmangamvvvm.repository.toPretty

class MangasManager(private val mangaService: MangaService) {
    suspend fun getLatestMangas() = runCatching {
        mangaService.getLatestMangas()
    }.toPretty()

    suspend fun getMangaDetails(link: String) = runCatching {
        mangaService.getMangaDetails(link)
    }.toPretty()

    suspend fun getMangaChapter(link: String) = runCatching{
        mangaService.getMangaChapter(link)
    }.toPretty()
}