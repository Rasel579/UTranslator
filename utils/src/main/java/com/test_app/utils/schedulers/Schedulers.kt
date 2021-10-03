package com.test_app.utils.schedulers

import kotlinx.coroutines.CoroutineDispatcher

interface Schedulers {
    fun io(): CoroutineDispatcher
    fun main(): CoroutineDispatcher
}