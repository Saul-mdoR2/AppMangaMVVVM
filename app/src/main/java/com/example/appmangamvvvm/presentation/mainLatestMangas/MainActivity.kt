package com.example.appmangamvvvm.presentation.mainLatestMangas

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.appmangamvvvm.R
import com.example.appmangamvvvm.databinding.ActivityMainBinding
import com.example.appmangamvvvm.presentation.mangaDetails.MangaDetailsActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val homeViewModel by viewModel<HomeViewModel>()
    private lateinit var layout: ActivityMainBinding
    private lateinit var mangasAdapter: RVLatestMangasAdapter

    companion object {
        const val TAG = "com.example.appmangamvvm.presentation.MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel.loading.postValue(true)
        setBindingLatout()
        initRecyclerView()
        initObservers()
        initToolbar()
    }

    override fun onResume() {
        Timber.d("MainActivity_TAG: onResume: ")
        super.onResume()
        homeViewModel.getAsyncMangas()
    }

    private fun setBindingLatout() {
        Timber.d("MainActivity_TAG: setBinding: ")
        layout = DataBindingUtil.setContentView(this, R.layout.activity_main)
        layout.lifecycleOwner = this
        layout.viewModel = homeViewModel
    }

    private fun initRecyclerView() {
        Timber.d("MainActivity_TAG: initRecyclerView: ")
        mangasAdapter =
            RVLatestMangasAdapter { manga ->
                Timber.d("MainActivity_TAG: initRecyclerView: itemClicked: ${manga.title}")
                startActivity(
                    Intent(
                        this,
                        MangaDetailsActivity::class.java
                    ).putExtra(
                        TAG,
                        manga
                    )
                )
            }
        layout.rvMangaList.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 3)
            adapter = mangasAdapter
        }
    }

    private fun initObservers() {
        Timber.d("MainActivity_TAG: initializeObservers: ")
        homeViewModel.availableMangasLD.observe(this, Observer { mangas ->
            Timber.d("MainActivity_TAG: initializeObservers: value changed: ${mangas.size}")
            mangasAdapter.itemList = mangas.map {
                MangaItemViewModel()
                    .apply { manga = it }
            }
            homeViewModel.loading.postValue(false)
        })
    }

    private fun initToolbar() {
        layout.mainToolbar.setTitleTextColor(Color.WHITE)
        layout.mainToolbar.title = resources.getString(R.string.title_toolbarMainPage)
        setSupportActionBar(layout.mainToolbar)
    }
}