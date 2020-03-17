package com.example.android_theerawuth_assignment.application.network

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

interface OkHttpClientService {
	fun getOkHttpClient(config: ApiConfig): OkHttpClient
}

class DefaultOkHttpClientService : OkHttpClientService {

	override fun getOkHttpClient(config: ApiConfig): OkHttpClient {
		return OkHttpClient.Builder()
				.connectTimeout(20, TimeUnit.SECONDS)
				.writeTimeout(20, TimeUnit.SECONDS)
				.readTimeout(20, TimeUnit.SECONDS)
				.build()
	}

}