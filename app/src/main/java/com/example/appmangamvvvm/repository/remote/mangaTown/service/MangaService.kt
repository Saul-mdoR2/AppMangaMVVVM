package com.example.appmangamvvvm.repository.remote.mangaTown.service

import com.example.appmangamvvvm.model.MangaDetailsModel
import com.example.appmangamvvvm.model.MangaModel

interface MangaService {
    suspend fun getLatestMangas(): List<MangaModel>
    suspend fun getMangaDetails(linkDetails: String): MangaDetailsModel
}