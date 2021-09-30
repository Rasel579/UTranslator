package com.professional.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest

class NetworkStatusImpl(context: Context) : NetworkStatus {
    private var statusObjects: Boolean = false

    init {
        val manager = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val request = NetworkRequest
            .Builder()
            .build()
        manager.registerNetworkCallback(
            request,
            object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    statusObjects = true
                }

                override fun onUnavailable() {
                    statusObjects = false
                }

                override fun onLost(network: Network) {
                    statusObjects = false
                }
            }
        )

    }

    override fun isOnline(): Boolean = statusObjects

}