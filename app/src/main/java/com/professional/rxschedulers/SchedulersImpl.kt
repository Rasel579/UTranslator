package com.professional.rxschedulers

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler

class SchedulersImpl : Schedulers {
    override fun io(): Scheduler = io.reactivex.rxjava3.schedulers.Schedulers.io()

    override fun main(): Scheduler = AndroidSchedulers.mainThread()
}