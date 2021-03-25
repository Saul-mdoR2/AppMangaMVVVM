package com.example.appmangamvvvm.presentation.mangaChapterViewer

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.appmangamvvvm.R
import com.example.appmangamvvvm.databinding.ActivityMangaChapterViewerBinding
import com.example.appmangamvvvm.model.ListChapterModel
import com.example.appmangamvvvm.model.MangaChapterPagesModel
import com.example.appmangamvvvm.presentation.mangaDetails.MangaDetailsActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MangaChapterViewer : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var layout: ActivityMangaChapterViewerBinding
    private val chapterViewerViewModel by viewModel<ViewerViewModel>()
    private var mangaChapter: ListChapterModel? = null
    private var mangaChapterActual: ListChapterModel? = null
    private var mangaChapterPagesModel: MangaChapterPagesModel? = null
    private var adapterChapters: ArrayAdapter<String>? = null
    var updateSpinner = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        chapterViewerViewModel.loading.postValue(true)
        mangaChapter = intent.getParcelableExtra(MangaDetailsActivity.TAG)!!
        mangaChapterActual = mangaChapter
        setBindingLatout()
        initObservers()

        if (layout.spListPages.onItemSelectedListener == null) {
            layout.spListPages.onItemSelectedListener = this
        }

        if (layout.spListChapters.onItemSelectedListener == null) {
            layout.spListChapters.onItemSelectedListener = this
        }

        initToolbar()
    }

    override fun onResume() {
        Timber.d("MangaDetailsActivity_TAG: onResume: ")
        super.onResume()
        val link = mangaChapter!!.linkChapter!!
        chapterViewerViewModel.getChapter(link)
    }

    private fun setBindingLatout() {
        Timber.d("MangaDetailsActivity_TAG: setBinding: ")
        layout = DataBindingUtil.setContentView(this, R.layout.activity_manga_chapter_viewer)
        layout.lifecycleOwner = this
        layout.viewModel = chapterViewerViewModel
    }

    private fun initObservers() {
        Timber.d("MangaChapterViewer: initializeObservers: ")
        chapterViewerViewModel.chapterLD.observe(this, Observer { mangaChapterModel ->
            Timber.d("MangaChapterViewer_TAG: initializeObservers: value changed: ${mangaChapterModel.currentPage}")
            val totalPages = mangaChapterModel.listPages!!.count() - 1
            layout.tvCurrentPage.text = resources.getString(
                R.string.CurrentPage,
                mangaChapterModel.currentPage,
                totalPages.toString()
            )
            mangaChapterPagesModel = mangaChapterModel

            initSpinners()

            chapterViewerViewModel.loading.postValue(false)
        })
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        chapterViewerViewModel.loading.postValue(true)
        when (keyCode) {
            KeyEvent.KEYCODE_VOLUME_DOWN -> {
                Timber.d("MangaChapterViewer_TAG: onKeyDown: vol down - previous page")
                val url = if (mangaChapterPagesModel!!.previousPage!!.contains("html")) {
                    mangaChapter!!.linkChapter + mangaChapterPagesModel!!.previousPage
                } else {
                    mangaChapterPagesModel!!.previousPage!!
                }
                chapterViewerViewModel.getChapter(url)
                return true
            }
            KeyEvent.KEYCODE_VOLUME_UP -> {
                Timber.d("MangaChapterViewer_TAG: onKeyDown: vol up - next page")
                val url = mangaChapter!!.linkChapter + mangaChapterPagesModel!!.nextPage
                if (mangaChapterPagesModel!!.nextPage!!.contains("featured")) {
                    Toast.makeText(this, "The chapter is over", Toast.LENGTH_SHORT).show()
                    chapterViewerViewModel.loading.postValue(false)
                    return true
                }
                chapterViewerViewModel.getChapter(url)
                return true
            }
            else -> super.onKeyDown(keyCode, event)
        }
        chapterViewerViewModel.loading.postValue(false)
        return super.onKeyDown(keyCode, event)
    }

    private fun initSpinners() {
        val numPages: ArrayList<String> = arrayListOf()
        for (page in mangaChapterPagesModel?.listPages!!) {
            numPages.add(page.numPage!!)
        }
        numPages.removeAt(numPages.count() - 1)
        val adapterPages = ArrayAdapter(applicationContext, R.layout.spinner_layout, numPages)
        layout.spListPages.adapter = adapterPages

        val titlesChapter: ArrayList<String> = arrayListOf()
        for (chapter in MangaDetailsActivity.chaptersList) {
            titlesChapter.add(chapter.titleChapter!!)

            adapterChapters =
                ArrayAdapter(applicationContext, R.layout.spinner_layout, titlesChapter)
            layout.spListChapters.adapter = adapterChapters
        }

        val positionPage = mangaChapterPagesModel?.currentPage!!.toInt() - 1
        layout.spListPages.post { layout.spListPages.setSelection(positionPage) }

        val positionChapter: Int = adapterChapters!!.getPosition(mangaChapter!!.titleChapter)
        layout.spListChapters.post { layout.spListChapters.setSelection(positionChapter) }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (parent) {
            layout.spListChapters -> {
                val elementSelected =
                    MangaDetailsActivity.chaptersList.first { listChapterModel ->
                        listChapterModel.titleChapter == parent.getItemAtPosition(position)
                    }
                mangaChapterActual = elementSelected
                if (mangaChapter!!.titleChapter != elementSelected.titleChapter) {
                    mangaChapter = elementSelected
                    layout.readerToolbar.title = elementSelected.titleChapter
                    chapterViewerViewModel.getChapter("${elementSelected.linkChapter}")
                }
            }
            layout.spListPages -> {
                val elementSelected =
                    mangaChapterPagesModel?.listPages!!.first { pagesModel ->
                        pagesModel.numPage == parent.getItemAtPosition(position)
                    }
                if (mangaChapterPagesModel!!.currentPage != elementSelected.numPage) {
                    chapterViewerViewModel.getChapter(elementSelected.linkPage!!)
                }
            }
        }
    }

    private fun initToolbar() {
        layout.readerToolbar.title = mangaChapter?.titleChapter
        layout.readerToolbar.setTitleTextColor(Color.WHITE)
        setSupportActionBar(layout.readerToolbar)
        layout.readerToolbar.setNavigationOnClickListener {
            finish()
        }
        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
    }

}