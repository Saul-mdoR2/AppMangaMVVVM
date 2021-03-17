package com.example.appmangamvvvm.repository.remote.mangaTown.service

import com.example.appmangamvvvm.BuildConfig
import com.example.appmangamvvvm.repository.remote.mangaTown.response.MangaDetailsResponse
import com.example.appmangamvvvm.repository.remote.mangaTown.response.MangaListMainResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitMangaService {
    @GET(BuildConfig.GET_MANGAS_PATH)
    suspend fun getLatestMangas(): Response<MangaListMainResponse>

    @GET("{linkDetails}")
    suspend fun getMangaDetails(@Path("linkDetails") link: String): Response<MangaDetailsResponse>
}