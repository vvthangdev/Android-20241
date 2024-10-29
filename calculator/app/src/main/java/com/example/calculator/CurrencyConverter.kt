package com.example.calculator

class CurrencyConverter {

    private val exchangeRates = mapOf(
        "USD" to 1.0,
        "EUR" to 0.85,
        "VND" to 24000.0,
        "GBP" to 0.75
    )

    fun convertCurrency(amount: Double, fromCurrency: String, toCurrency: String): Double {
        val rateFrom = exchangeRates[fromCurrency] ?: 1.0
        val rateTo = exchangeRates[toCurrency] ?: 1.0
        return (amount / rateFrom) * rateTo
    }
}
