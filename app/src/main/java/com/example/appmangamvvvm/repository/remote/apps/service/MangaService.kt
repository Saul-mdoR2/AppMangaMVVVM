package com.example.appmangamvvvm.repository.remote.apps.service

import com.example.appmangamvvvm.model.MangaMainModel

interface MangaService {
    suspend fun getMangas():MangaMainModel
}