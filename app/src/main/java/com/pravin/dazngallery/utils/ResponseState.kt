package com.pravin.dazngallery.utils


/**
 * Class to handle the state of the data response
 */
sealed class ResponseState<out R> {
    data class Loaded<out T>(val data: T) : ResponseState<T>()
    data class Error(val exception: Exception) : ResponseState<Nothing>()
    data class Loading<T>(val data: T? = null) : ResponseState<T>()

}