package com.example.appmangamvvvm.repository.remote.apps.service

interface MangaService {
    // Regresa un string porque la petición va a regresar el HTML de la pagina(?), y con este string (el HTML) voy a sacar los datos con Jspoon
    suspend fun getHtmlMangas():String
}