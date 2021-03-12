package com.example.appmangamvvvm.presentation

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import timber.log.Timber

@BindingAdapter(
    value = ["imageUrl"],
    requireAll = false
)

fun imageUrl(view: ImageView, coverUrl: String?) {
    if (coverUrl.isNullOrEmpty()) {
        view.setImageDrawable(null)
        view.background = null
        return
    }
    try {
        when {
            coverUrl.isNotEmpty() -> {
                view.setImageDrawable(null)
                view.background = null
                view.colorFilter = null
                Glide.with(view).load(coverUrl).into(view)
            }
        }
    } catch (e: Exception) {
        Timber.d("ViewBindingAdapters_TAG: imageUrl: exception: $e")
    }
}

@BindingAdapter("android:visibility")
fun visibility(view: View, visible: Boolean?) = when (visible) {
    true -> view.visibility = View.VISIBLE
    else -> view.visibility = View.GONE
}