package com.professional.rxschedulers

import io.reactivex.rxjava3.core.Scheduler

interface Schedulers {
    fun io() :Scheduler
    fun main(): Scheduler
}