package com.example.restourantreview.util

// membuat event wrapper
open class Event<out T>(private val content: T) {

    @Suppress("MemberVisibilityCanBePrivate")
    var hasBeenHandled = false
        private set

    // memeriksa apa aksi ini pernah di eksekusi seblumnya
    fun getContentIfNotHandled(): T?{
        return if (hasBeenHandled){
            null
        }else{
            hasBeenHandled= true
            content
        }
    }

    fun peekContent(): T = content
}