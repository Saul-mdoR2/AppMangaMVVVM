package com.example.appmangamvvvm.repository.remote.mangaTown.service

import com.example.appmangamvvvm.model.*
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
                        ListChapterModel(
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

    override suspend fun getMangaChapter(linkChapter: String): MangaChapterPagesModel {
        return retrofitMangaService.getMangaChapter(linkChapter)
            .decodeResponse { mangaChapterResponse ->
                MangaChapterPagesModel(
                    currentPage = mangaChapterResponse.currentPage ?: "",
                    previousPage = mangaChapterResponse.previousPage ?: "",
                    nextPage = mangaChapterResponse.nextPage ?: "",
                    currentPageLink = mangaChapterResponse.currentPageLink ?: "",
                    listPages = mangaChapterResponse.listPages!!.map { pagesResponse ->
                        PagesModel(
                            numPage = pagesResponse.numPage ?: "",
                            linkPage = pagesResponse.linkPage ?: ""
                        )
                    }
                )
            }
    }
}

