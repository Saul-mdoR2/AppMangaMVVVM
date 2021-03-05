package com.example.appmangamvvvm.repository.remote.apps.service

import com.example.appmangamvvvm.BuildConfig
import com.example.appmangamvvvm.repository.remote.apps.response.MangaMainResponse
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitMangasService {
    @GET(BuildConfig.GET_MANGAS_PATH)
    suspend fun getMangas():Response<MangaMainResponse>
}