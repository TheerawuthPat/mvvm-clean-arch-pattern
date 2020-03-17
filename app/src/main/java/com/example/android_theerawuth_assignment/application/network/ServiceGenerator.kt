package com.example.android_theerawuth_assignment.application.network

import com.example.android_theerawuth_assignment.application.network.interceptor.HttpNetworkInterceptor
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

interface ServiceGenerator {
	fun <T> create(apiConfig: ApiConfig, clazz: Class<T>): T
}

class DefaultServiceGenerator constructor(
		private val okHttpClientService: OkHttpClientService,
		private val httpNetworkInterceptor: HttpNetworkInterceptor
) : ServiceGenerator {

	override fun <T> create(apiConfig: ApiConfig, clazz: Class<T>): T {

		val httpClient = okHttpClientService.getOkHttpClient(apiConfig)
				.newBuilder()

		val logger = HttpLoggingInterceptor(
				HttpLoggingInterceptor.Logger { message -> Timber.i(message) })
		logger.level = HttpLoggingInterceptor.Level.BODY

		httpClient.addInterceptor(logger)

		// add network monitor interceptor:
		httpClient.addInterceptor(httpNetworkInterceptor)

		val client = httpClient.build()
		val retrofit = Retrofit.Builder()
				.addConverterFactory(GsonConverterFactory.create())
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.baseUrl(apiConfig.baseURL)
				.client(client)
				.build()

		return retrofit.create(clazz)
	}

}