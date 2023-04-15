package com.example.spending.ui.main.screen.home

sealed class ResultHome<T>(
    val data: T? = null,
    val message: String? = null,
    val totalAmount: Int? = null,
    val currency: String? = null,
) {
    class Success<T>(data: T, totalAmount: Int, currency: String) :
        ResultHome<T>(data, totalAmount = totalAmount, currency = currency)

    class Error<T>(message: String, data: T? = null) : ResultHome<T>(data, message)
    class Empty<T> : ResultHome<T>()
}