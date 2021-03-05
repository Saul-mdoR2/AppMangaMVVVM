package com.example.appmangamvvvm.repository.remote

import retrofit2.Response

fun <I, O> Response<I>.decodeResponse(onSuccess: (I) -> O): O =
        when {
            isSuccessful -> body()?.let {
                onSuccess(it)
            }
            else -> throw Exception(errorBody()?.string())
        } ?: throw RuntimeException("Error executing API call")
