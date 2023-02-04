package com.example.bookapi.domain.usecase.datamodel

sealed class IResult<T>(val status: Status,val data: List<T>? = null,val message: String? = null) {
    class Success<T>(data: List<T>?): IResult<T>(Status.SUCCESS, data)
    class Error<T>(message: String): IResult<T>(Status.ERROR, message = message)
    class Loading<T>(isLoading: Boolean): IResult<T>(Status.LOADING)
}
