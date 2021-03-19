package com.example.appmangamvvvm.presentation

import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.appmangamvvvm.R
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
                Glide.with(view).load(coverUrl).placeholder(R.drawable.notfound).into(view)
            }
        }
    } catch (e: Exception) {
        Timber.d("ViewBindingAdapters_TAG: imageUrl: exception: $e")
    }
}

@BindingAdapter(
    value = ["pageUrl"]
)
fun pageUrl(view: ImageView, pageUrl: String?) {
    if (pageUrl.isNullOrEmpty()) {
        val resourceId =
            view.context.resources.getIdentifier("notfound", "drawable", view.context.packageName)
        val drawable = ContextCompat.getDrawable(
            view.context,
            resourceId
        )
        view.setImageDrawable(drawable)
        view.background = null
        return
    }
    try {
        when {
            pageUrl.isNotEmpty() -> {
                view.setImageDrawable(null)
                view.background = null
                view.colorFilter = null
                val url = "http:${pageUrl}"
                Glide.with(view).load(url).placeholder(R.drawable.notfound).into(view)
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