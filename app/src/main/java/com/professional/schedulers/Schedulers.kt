package com.professional.schedulers

import kotlinx.coroutines.CoroutineDispatcher

interface Schedulers {
    fun io(): CoroutineDispatcher
    fun main(): CoroutineDispatcher
}