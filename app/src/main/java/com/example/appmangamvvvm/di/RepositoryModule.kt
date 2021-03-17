package com.example.appmangamvvvm.di

import com.example.appmangamvvvm.BuildConfig
import com.example.appmangamvvvm.repository.remote.mangaTown.MangasManager
import com.example.appmangamvvvm.repository.remote.mangaTown.service.MangaService
import com.example.appmangamvvvm.repository.remote.mangaTown.service.MangaServiceImplementation
import com.example.appmangamvvvm.repository.remote.mangaTown.service.RetrofitMangaService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
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
    single<RetrofitMangaService> { createRetrofitWebService(get(), BuildConfig.API_SERVER) }
    single<MangaService> { MangaServiceImplementation(get()) }
    single { MangasManager(get()) }
}

val repositoryModule = listOf(apiModule, mangasModule)