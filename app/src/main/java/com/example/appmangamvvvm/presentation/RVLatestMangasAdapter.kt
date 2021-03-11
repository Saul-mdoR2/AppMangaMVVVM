package com.example.appmangamvvvm.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.appmangamvvvm.databinding.ItemsLayoutBinding
import com.example.appmangamvvvm.model.MangaModel

class RVLatestMangasAdapter(listener: (MangaModel)->Unit): BaseRVAdapter<MangaModel, MangaItemViewModel, ItemsLayoutBinding>(listener) {
    override fun inflateDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ItemsLayoutBinding = ItemsLayoutBinding.inflate(inflater, container, false)

    override fun getBindItem(itemViewModel: MangaItemViewModel): MangaModel? = itemViewModel.manga
}