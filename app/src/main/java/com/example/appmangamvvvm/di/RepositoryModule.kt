package com.example.appmangamvvvm.di

import androidx.viewpager.widget.PagerAdapter
import com.example.appmangamvvvm.BuildConfig
import com.example.appmangamvvvm.repository.remote.apps.MangasManager
import com.example.appmangamvvvm.repository.remote.apps.service.MangaService
import com.example.appmangamvvvm.repository.remote.apps.service.MangaServiceImplementation
import com.example.appmangamvvvm.repository.remote.apps.service.RetrofitMangasService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import pl.droidsonroids.jspoon.Jspoon
import pl.droidsonroids.retrofit2.JspoonConverterFactory
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

val apiModule = module {
    single { createOkHttpClient() }

}

private val logLevel =
        if (!BuildConfig.DEBUG) HttpLoggingInterceptor.Level.NONE
        else HttpLoggingInterceptor.Level.BODY

fun createOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply { level = logLevel })
        .readTimeout(120, TimeUnit.SECONDS)
        .build()

inline fun <reified T> createRetrofitWebService(okHttpClient: OkHttpClient, url: String): T =
        Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient)
                .addConverterFactory(JspoonConverterFactory.create())
                .build()
                .create(T::class.java)


val mangasModule = module {
    single<RetrofitMangasService> { createRetrofitWebService(get(), BuildConfig.API_SERVER) }
    single<MangaService> { MangaServiceImplementation(get()) }
    single { MangasManager(get()) }
}

val repositoryModule = listOf(apiModule, mangasModule)