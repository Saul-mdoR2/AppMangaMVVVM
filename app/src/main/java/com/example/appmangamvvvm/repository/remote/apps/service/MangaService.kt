package com.example.appmangamvvvm.repository.remote.apps.service

import com.example.appmangamvvvm.model.MangaModel

interface MangaService {
    suspend fun getLatestMangas(): List<MangaModel>
}