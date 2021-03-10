package com.example.appmangamvvvm.repository.remote.apps.service

import com.example.appmangamvvvm.repository.remote.decodeResponse

class MangaServiceImplementation(private val retrofitMangasService: RetrofitMangasService) : MangaService {

    override suspend fun getHtmlMangas(): String {
         return retrofitMangasService.getHtmlMangas().decodeResponse {
              html: String ->
              html
            }
        }
    }

