package com.example.appmangamvvvm.repository.remote.apps.service

import com.example.appmangamvvvm.BuildConfig
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitMangasService {
    // Regresa un string porque la petición va a regresar el HTML de la pagina(?), y con este string (el HTML) voy a sacar los datos con Jspoon
    @GET(BuildConfig.GET_MANGAS_PATH)
    suspend fun getHtmlMangas():Response<String>
}