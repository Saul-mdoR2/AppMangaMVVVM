package com.example.appmangamvvvm.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appmangamvvvm.model.MangaDetailsModel
import com.example.appmangamvvvm.repository.handle
import com.example.appmangamvvvm.repository.remote.mangaTown.MangasManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import timber.log.Timber

class MangaDetailsViewModel(private val mangaManager: MangasManager) : ViewModel() {
    var mangaDetailsLD = MutableLiveData<MangaDetailsModel>()
    var loading = MutableLiveData<Boolean>(true)

    fun getMangaDetails(link: String) {
        CoroutineScope(Dispatchers.IO).launch {
            mangaManager.getMangaDetails(link).handle(
                error = { exception ->
                    Timber.d("MainActivity_TAG: onCreate: onMangasCall: error: $exception")
                },
                success = { mangaDetailsModel ->
                    Timber.d("MangaDetailsActivity_TAG: onCreate: onMangasCall: ${mangaDetailsModel.title}")
                    mangaDetailsModel.summary = mangaDetailsModel.summary?.replace(" HIDE", "")
                    mangaDetailsLD.postValue(mangaDetailsModel)
                }
            )
        }
    }
}