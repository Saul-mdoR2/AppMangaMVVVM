package com.example.appmangamvvvm.repository.remote.mangaDetails.service

import com.example.appmangamvvvm.repository.remote.mangaDetails.response.MangaDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitMangaDetailsService {

    @GET("{linkDetails}")
    suspend fun getMangaDetails(@Path("linkDetails") link:String): MangaDetailsResponse
}