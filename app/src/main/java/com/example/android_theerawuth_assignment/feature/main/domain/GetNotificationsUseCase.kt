package com.example.android_theerawuth_assignment.feature.main.domain

import com.example.android_theerawuth_assignment.application.core.UseCase
import com.example.android_theerawuth_assignment.application.network.Results
import com.example.android_theerawuth_assignment.feature.main.datasource.MainRepository

abstract class GetNotificationsUseCase : UseCase<String, Results<List<NotificationInfo>>>()

class GetNotificationsUseCaseImpl(private val repository: MainRepository) :
		GetNotificationsUseCase() {

	override suspend fun execute(request: String): Results<List<NotificationInfo>> {
		return repository.getNotifications(request)
	}

}