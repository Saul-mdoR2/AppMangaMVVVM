package com.example.appmangamvvvm.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.appmangamvvvm.databinding.ChaptersLayoutBinding
import com.example.appmangamvvvm.model.ChapterModel
import com.example.appmangamvvvm.model.MangaDetailsModel

class RVListMangaChapters(listener: (ChapterModel)->Unit):BaseRVAdapter<ChapterModel, ChapterItemViewModel, ChaptersLayoutBinding>(listener) {
    override fun inflateDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ChaptersLayoutBinding = ChaptersLayoutBinding.inflate(inflater, container, false)

    override fun getBindItem(itemViewModel: ChapterItemViewModel): ChapterModel? = itemViewModel.chapter
}