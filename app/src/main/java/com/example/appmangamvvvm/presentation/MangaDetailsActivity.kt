package com.example.appmangamvvvm.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.appmangamvvvm.R
import com.example.appmangamvvvm.databinding.ActivityMangaDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MangaDetailsActivity : AppCompatActivity() {

    private lateinit var layout: ActivityMangaDetailsBinding
    private lateinit var adapter: RVListMangaChapters
    private val mangaDetailsViewModel by viewModel<MangaDetailsViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBindingLatout()
    }

    private fun setBindingLatout() {
        Timber.d("MainActivity_TAG: setBinding: ")
        layout = DataBindingUtil.setContentView(this, R.layout.activity_manga_details)
        layout.lifecycleOwner = this
        layout.viewModel = mangaDetailsViewModel
    }
}