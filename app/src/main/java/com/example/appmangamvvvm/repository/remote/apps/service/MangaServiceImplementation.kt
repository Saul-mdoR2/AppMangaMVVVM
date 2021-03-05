package com.example.appmangamvvvm.repository.remote.apps.service

import com.example.appmangamvvvm.model.MangaMainModel

class MangaServiceImplementation (private val retrofitMangasService: RetrofitMangasService):MangaService{
    override suspend fun getMangas(): MangaMainModel {
        TODO("Not yet implemented")
    }

}