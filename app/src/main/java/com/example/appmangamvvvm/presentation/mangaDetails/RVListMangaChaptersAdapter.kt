package com.example.appmangamvvvm.presentation.mangaDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.appmangamvvvm.databinding.ChaptersLayoutBinding
import com.example.appmangamvvvm.model.ListChapterModel
import com.example.appmangamvvvm.presentation.BaseRVAdapter

class RVListMangaChaptersAdapter(listener: (ListChapterModel) -> Unit) :
    BaseRVAdapter<ListChapterModel, ChapterItemViewModel, ChaptersLayoutBinding>(listener) {
    override fun inflateDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ChaptersLayoutBinding = ChaptersLayoutBinding.inflate(inflater, container, false)

    override fun getBindItem(itemViewModel: ChapterItemViewModel): ListChapterModel? =
        itemViewModel.chapter
}