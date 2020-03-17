package com.example.mvvm_livedata_project.application.network

import android.content.Context
import android.net.ConnectivityManager

interface NetworkConnection {
	fun isNetworkConnected(): Boolean
}

class DefaultNetworkConnection(private val context: Context) : NetworkConnection {
	override fun isNetworkConnected(): Boolean {
		val connectivityManager = context.getSystemService(
				Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
		return connectivityManager?.activeNetworkInfo != null
	}
}