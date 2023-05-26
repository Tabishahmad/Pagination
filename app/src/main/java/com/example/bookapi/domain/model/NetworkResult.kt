package com.example.bookapi.domain.model

sealed class NetworkResult<out T : Any> {
    data class Success<out T : Any>(val data: List<T>) : NetworkResult<T>()
    data class Failure  (val failMessage: String) : NetworkResult<Nothing>()
}