package com.example.appmangamvvvm.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appmangamvvvm.model.MangaModel
import com.example.appmangamvvvm.repository.handle
import com.example.appmangamvvvm.repository.remote.mangaTown.MangasManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeViewModel(private val mangasManager: MangasManager) : ViewModel() {
    var availableMangasLD = MutableLiveData<List<MangaModel>>()
    var loading = MutableLiveData<Boolean>(true)

    fun getAsyncMangas() {
        CoroutineScope(Dispatchers.IO).launch {
            mangasManager.getLatestMangas().handle(
                error = { exception ->
                    Timber.d("MainActivity_TAG: onCreate: onMangasCall: error: $exception")
                },
                success = { mangasResult ->
                    Timber.d("MainActivity_TAG: onCreate: onMangasCall: ${mangasResult.size}")
                    availableMangasLD.postValue(mangasResult)
                }
            )
        }
    }
}