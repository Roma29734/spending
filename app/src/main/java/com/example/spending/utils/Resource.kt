package com.example.spending.utils

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null,
    val totalAmount: Int? = null
) {
    class Success<T>(data: T, totalAmount: Int) : Resource<T>(data, totalAmount = totalAmount)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Empty<T> : Resource<T>()
}
