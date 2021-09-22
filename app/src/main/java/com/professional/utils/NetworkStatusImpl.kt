package com.professional.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.BehaviorSubject
import javax.inject.Inject

class NetworkStatusImpl @Inject constructor(context: Context) : NetworkStatus {
    private val statusObjects: BehaviorSubject<Boolean> = BehaviorSubject.create()

    init {
        statusObjects.onNext(false)
        val manager = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val request = NetworkRequest
            .Builder()
            .build()
        manager.registerNetworkCallback(
            request,
            object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    statusObjects.onNext(true)
                }

                override fun onUnavailable() {
                    statusObjects.onNext(false)
                }

                override fun onLost(network: Network) {
                    statusObjects.onNext(false)
                }
            }
        )

    }

    override fun isOnline(): Observable<Boolean> = statusObjects
    override fun onLineSingle(): Single<Boolean> = statusObjects.first(false)


}