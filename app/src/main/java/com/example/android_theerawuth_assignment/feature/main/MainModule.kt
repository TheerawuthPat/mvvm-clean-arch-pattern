package com.example.android_theerawuth_assignment.feature.main

import com.example.android_theerawuth_assignment.application.network.ServiceGenerator
import com.example.android_theerawuth_assignment.feature.main.datasource.MainDataSource
import com.example.android_theerawuth_assignment.feature.main.datasource.MainRepository
import com.example.android_theerawuth_assignment.feature.main.datasource.MainRepositoryImpl
import com.example.android_theerawuth_assignment.feature.main.datasource.remote.MainApi
import com.example.android_theerawuth_assignment.feature.main.datasource.remote.MainRemoteDataSource
import com.example.android_theerawuth_assignment.feature.main.domain.GetNotificationsUseCase
import com.example.android_theerawuth_assignment.feature.main.domain.GetNotificationsUseCaseImpl
import com.example.android_theerawuth_assignment.feature.main.domain.GetProfileUseCase
import com.example.android_theerawuth_assignment.feature.main.domain.GetProfileUseCaseImpl
import com.example.android_theerawuth_assignment.feature.main.presenter.MainActivity
import com.example.android_theerawuth_assignment.feature.main.presenter.MainViewModel
import com.example.android_theerawuth_assignment.feature.main.presenter.list.NotificationListAdapter
import org.koin.androidx.experimental.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
	scope<MainActivity> {
		factory<MainRepository> { MainRepositoryImpl(get()) }
		factory<GetNotificationsUseCase> { GetNotificationsUseCaseImpl(get()) }
		factory<GetProfileUseCase> { GetProfileUseCaseImpl(get()) }
		factory<MainDataSource> { MainRemoteDataSource(get()) }
		viewModel<MainViewModel>()
		factory { get<ServiceGenerator>().create(get(), MainApi::class.java) }
		factory { NotificationListAdapter() }

	}
}