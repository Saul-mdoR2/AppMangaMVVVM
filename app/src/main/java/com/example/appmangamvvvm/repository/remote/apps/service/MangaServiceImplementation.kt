package com.example.appmangamvvvm.repository.remote.apps.service

class MangaServiceImplementation(private val retrofitMangasService: RetrofitMangasService) : MangaService {
    override suspend fun getHtmlMangas(): String {
        return retrofitMangasService.getHtmlMangas()
    }

}