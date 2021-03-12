package com.example.appmangamvvvm.repository.remote.apps.service

import com.example.appmangamvvvm.model.MangaModel
import com.example.appmangamvvvm.repository.remote.decodeResponse
import timber.log.Timber

class MangaServiceImplementation(
    private val retrofitMangasService: RetrofitMangasService
) : MangaService {
    private var chacheLatestMangas = emptyList<MangaModel>()

    override suspend fun getLatestMangas(): List<MangaModel> {
        if(chacheLatestMangas.isNotEmpty()){
            Timber.d("AppsServiceImpl_TAG: getLatestMangas: response from cache")
            return chacheLatestMangas
        }

        return retrofitMangasService.getLatestMangas().decodeResponse { response ->
            response.listMangas.map { mangaResponse ->
                MangaModel(
                    imageUrl = mangaResponse.imageUrl,
                    title = mangaResponse.title,
                    latestChapter = mangaResponse.latestChapter,
                    detailsUrl = mangaResponse.detailsUrl
                )
            }
                 .also {
                chacheLatestMangas = it
            }
        }
    }
}

