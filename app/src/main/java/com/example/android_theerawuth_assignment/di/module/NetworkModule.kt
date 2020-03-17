package com.example.android_theerawuth_assignment.di.module

import com.example.android_theerawuth_assignment.application.network.ApiConfig
import com.example.android_theerawuth_assignment.application.network.DefaultOkHttpClientService
import com.example.android_theerawuth_assignment.application.network.DefaultServiceGenerator
import com.example.android_theerawuth_assignment.application.network.OkHttpClientService
import com.example.android_theerawuth_assignment.application.network.ServiceGenerator
import com.example.android_theerawuth_assignment.application.network.interceptor.HttpNetworkInterceptor
import com.example.mvvm_livedata_project.application.network.DefaultNetworkConnection
import com.example.mvvm_livedata_project.application.network.NetworkConnection
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val networkModule = module {
	single { ApiConfig("https://testapi.io/api/razir/") }
	single { HttpNetworkInterceptor(get()) }
	single<NetworkConnection> { DefaultNetworkConnection(androidContext()) }
	single<OkHttpClientService> { DefaultOkHttpClientService() }
	single<ServiceGenerator> {
		DefaultServiceGenerator(
				okHttpClientService = get(),
				httpNetworkInterceptor = get()
		)
	}
}