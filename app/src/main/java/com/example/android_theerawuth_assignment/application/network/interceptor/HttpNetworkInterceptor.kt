package com.example.android_theerawuth_assignment.application.network.interceptor

import com.example.android_theerawuth_assignment.application.exception.NoNetworkException
import com.example.mvvm_livedata_project.application.network.NetworkConnection
import okhttp3.Interceptor
import okhttp3.Response

class HttpNetworkInterceptor constructor(
		private val networkConnection: NetworkConnection
) : Interceptor {
	override fun intercept(chain: Interceptor.Chain): Response {
		if (networkConnection.isNetworkConnected()) {
			return chain.proceed(chain.request())
		} else {
			throw NoNetworkException()
		}
	}
}