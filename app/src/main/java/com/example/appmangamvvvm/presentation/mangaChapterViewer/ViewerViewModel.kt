package com.example.appmangamvvvm.presentation.mangaChapterViewer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appmangamvvvm.model.MangaChapterPagesModel
import com.example.appmangamvvvm.repository.handle
import com.example.appmangamvvvm.repository.remote.mangaTown.MangasManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import timber.log.Timber

class ViewerViewModel(private val mangaManager: MangasManager) : ViewModel() {
    var chapterLD = MutableLiveData<MangaChapterPagesModel>()
    var loading = MutableLiveData<Boolean>(true)

    fun getChapter(link: String) {
        CoroutineScope(Dispatchers.IO).launch {
            mangaManager.getMangaChapter(link).handle(
                error = { exception ->
                    Timber.d("MangaChapterViewer: onCreate: onMangasCall: error: $exception")
                },
                success = { mangaChapterModel ->
                    Timber.d("MangaChapterViewer: onCreate: onMangasCall: ${mangaChapterModel.currentPage}")
                    chapterLD.postValue(mangaChapterModel)
                }
            )
        }
    }
}