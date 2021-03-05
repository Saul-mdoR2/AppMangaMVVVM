package com.example.appmangamvvvm.repository

sealed class CompletedPretty {
    abstract val success: Boolean

    object Success : CompletedPretty() {
        override val success: Boolean
            get() = true
    }

    class Error(val throwable: Throwable) : CompletedPretty() {
        override val success: Boolean
            get() = false
    }
}