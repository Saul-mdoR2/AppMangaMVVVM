package com.example.appmangamvvvm.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appmangamvvvm.R
import com.example.appmangamvvvm.repository.handle
import com.example.appmangamvvvm.repository.remote.apps.MangasManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private val mangasManager by inject<MangasManager>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(Dispatchers.IO).launch {
            mangasManager.getHtmlMangas().handle(
                    error = { exception ->
                        Timber.d("MainActivity_TAG: onCreate: onMangasCall: error: $exception")
                    },
                    success = { mangasResult ->
                        Timber.d("MainActivity_TAG: onCreate: onMangasCall: ${mangasResult.size}")
                    }
            )
        }
    }
}