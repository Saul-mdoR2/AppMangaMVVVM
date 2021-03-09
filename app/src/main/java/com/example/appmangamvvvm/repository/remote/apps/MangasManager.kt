package com.example.appmangamvvvm.repository.remote.apps

import com.example.appmangamvvvm.repository.remote.apps.service.MangaService
import com.example.appmangamvvvm.repository.toPretty

class MangasManager(private val mangaService: MangaService) {
    suspend fun getMangas() = runCatching {
        mangaService.getHtmlMangas()
    }.toPretty()
}