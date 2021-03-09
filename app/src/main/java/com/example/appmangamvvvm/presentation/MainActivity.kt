package com.example.appmangamvvvm.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appmangamvvvm.R
import com.example.appmangamvvvm.model.MangaListMainModel
import com.example.appmangamvvvm.repository.handle
import com.example.appmangamvvvm.repository.remote.apps.MangasManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import pl.droidsonroids.jspoon.HtmlAdapter
import pl.droidsonroids.jspoon.Jspoon
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}