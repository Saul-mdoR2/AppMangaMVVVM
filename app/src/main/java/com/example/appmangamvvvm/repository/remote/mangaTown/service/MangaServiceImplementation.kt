package com.example.appmangamvvvm.repository.remote.mangaTown.service

import com.example.appmangamvvvm.model.ChapterModel
import com.example.appmangamvvvm.model.MangaDetailsModel
import com.example.appmangamvvvm.model.MangaModel
import com.example.appmangamvvvm.repository.remote.decodeResponse
import timber.log.Timber

class MangaServiceImplementation(
    private val retrofitMangaService: RetrofitMangaService
) : MangaService {
    private var chacheLatestMangas = emptyList<MangaModel>()

    override suspend fun getLatestMangas(): List<MangaModel> {
        if (chacheLatestMangas.isNotEmpty()) {
            Timber.d("AppsServiceImpl_TAG: getLatestMangas: response from cache")
            return chacheLatestMangas
        }

        return retrofitMangaService.getLatestMangas().decodeResponse { response ->
            response.listMangas.map { mangaResponse ->
                MangaModel(
                    imageUrl = mangaResponse.imageUrl ?: "",
                    title = mangaResponse.title ?: "",
                    latestChapter = mangaResponse.latestChapter ?: "",
                    detailsUrl = mangaResponse.detailsUrl ?: ""
                )
            }
                .also {
                    chacheLatestMangas = it
                }
        }
    }

    override suspend fun getMangaDetails(linkDetails: String): MangaDetailsModel {

        return retrofitMangaService.getMangaDetails(linkDetails)
            .decodeResponse { mangaDetailsResponse ->
                MangaDetailsModel(
                    title = mangaDetailsResponse.title ?: "",
                    genre = mangaDetailsResponse.genre ?: "",
                    author = mangaDetailsResponse.author ?: "",
                    status = mangaDetailsResponse.status ?: "",
                    artist = mangaDetailsResponse.artist ?: "",
                    type = mangaDetailsResponse.type ?: "",
                    imageCover = mangaDetailsResponse.imageCover ?: "",
                    summary = mangaDetailsResponse.summary ?: "",
                    chaptersList = mangaDetailsResponse.chaptersList.map { chapterResponse ->
                        ChapterModel(
                            titleChapter = chapterResponse.titleChapter!!.replace(
                                mangaDetailsResponse.title!!,
                                "Chapter"
                            ),
                            releaseDate = chapterResponse.releaseDate ?: "",
                            linkChapter = chapterResponse.linkChapter ?: ""
                        )
                    }
                )
            }
    }
}

