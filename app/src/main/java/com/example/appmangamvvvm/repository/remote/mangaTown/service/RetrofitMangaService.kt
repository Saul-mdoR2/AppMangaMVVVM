package com.example.appmangamvvvm.repository.remote.mangaTown.service

import com.example.appmangamvvvm.BuildConfig
import com.example.appmangamvvvm.repository.remote.mangaTown.response.MangaChapterResponse
import com.example.appmangamvvvm.repository.remote.mangaTown.response.MangaDetailsResponse
import com.example.appmangamvvvm.repository.remote.mangaTown.response.MangaListMainResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitMangaService {
    @GET(BuildConfig.GET_MANGAS_PATH)
    suspend fun getLatestMangas(): Response<MangaListMainResponse>

    @GET(BuildConfig.GET_MANGA_DETAILS)
    suspend fun getMangaDetails(@Path("path") link: String): Response<MangaDetailsResponse>

    @GET(BuildConfig.GET_MANGA_PAGE)
    suspend fun getMangaChapter(@Path("path") link: String): Response<MangaChapterResponse>
}