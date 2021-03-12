package com.example.appmangamvvvm.repository.remote.mangaDetails.service

import com.example.appmangamvvvm.model.MangaDetailsModel

interface MangaDetailsService {
    suspend fun getDetails(link:String):MangaDetailsModel
}