package com.example.appmangamvvvm.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.appmangamvvvm.R
import com.example.appmangamvvvm.databinding.ActivityMangaDetailsBinding
import com.example.appmangamvvvm.model.MangaModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MangaDetailsActivity : AppCompatActivity() {

    private lateinit var layout: ActivityMangaDetailsBinding
    private lateinit var rvChaptersAdapter: RVListMangaChaptersAdapter
    private val mangaDetailsViewModel by viewModel<MangaDetailsViewModel>()
    private var manga: MangaModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mangaDetailsViewModel.loading.postValue(true)

        manga = intent.getParcelableExtra(MainActivity.TAG)!!

        setBindingLatout()
        initObservers()
        initRecyclerView()
    }

    override fun onResume() {
        Timber.d("MangaDetailsActivity_TAG: onResume: ")
        super.onResume()
        mangaDetailsViewModel.getMangaDetails(manga!!.detailsUrl!!)
    }

    private fun setBindingLatout() {
        Timber.d("MainActivity_TAG: setBinding: ")
        layout = DataBindingUtil.setContentView(this, R.layout.activity_manga_details)
        layout.lifecycleOwner = this
        layout.viewModel = mangaDetailsViewModel
    }

    private fun initRecyclerView() {
        Timber.d("MainActivity_TAG: initRecyclerView: ")
        rvChaptersAdapter = RVListMangaChaptersAdapter { chapter ->
            Timber.d("MainActivity_TAG: initRecyclerView2: itemClicked: ${chapter.titleChapter}")

            // ABRIR SIGUIENTE ACTIVIDAD
            //  startActivity(Intent(this, MangaDetailsActivity::class.java))
            Toast.makeText(this@MangaDetailsActivity, chapter.titleChapter, Toast.LENGTH_SHORT)
                .show()

        }
        layout.rvChapters.apply {
            layoutManager = GridLayoutManager(this@MangaDetailsActivity, 4)
            adapter = rvChaptersAdapter
        }
    }

    private fun initObservers() {
        Timber.d("MangaDetailsActivity_TAG: initializeObservers: ")
        mangaDetailsViewModel.mangaDetailsLD.observe(this, Observer { mangaDetailsModel ->
            Timber.d("MangaDetailsActivity_TAG: initializeObservers: value changed: ${mangaDetailsModel.title}")
            if (mangaDetailsModel.chaptersList == null) {
                Toast.makeText(
                    this,
                    "${mangaDetailsModel?.title} it's not available in MangaTown",
                    Toast.LENGTH_LONG
                )
                    .show()
            }
            rvChaptersAdapter.itemList = mangaDetailsModel.chaptersList!!.map {
                ChapterItemViewModel().apply { chapter = it }
            }
            mangaDetailsViewModel.loading.postValue(false)
        })
    }
}