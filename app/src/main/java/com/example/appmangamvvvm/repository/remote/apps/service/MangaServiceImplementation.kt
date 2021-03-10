package com.example.appmangamvvvm.repository.remote.apps.service

import com.example.appmangamvvvm.model.MangaModel
import com.example.appmangamvvvm.repository.remote.decodeResponse

class MangaServiceImplementation(
    private val retrofitMangasService: RetrofitMangasService
) : MangaService {

    override suspend fun getLatestMangas(): List<MangaModel> {
        return retrofitMangasService.getLatestMangas().decodeResponse { response ->
            response.listMangas.map { mangaResponse ->
                MangaModel(
                    image = mangaResponse.imageUrl,
                    title = mangaResponse.title,
                    latestChapter = mangaResponse.latestChapter,
                    detailsUrl = mangaResponse.detailsUrl
                )
            }
        }
    }
}

