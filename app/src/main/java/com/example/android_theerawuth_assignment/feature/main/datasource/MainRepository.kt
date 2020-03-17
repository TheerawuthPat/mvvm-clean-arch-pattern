package com.example.android_theerawuth_assignment.feature.main.datasource

import com.example.android_theerawuth_assignment.application.network.Results
import com.example.android_theerawuth_assignment.feature.main.domain.NotificationsModel
import com.example.android_theerawuth_assignment.feature.main.domain.ProfileDataModel

interface MainRepository {
	suspend fun getProfile(): Results<ProfileDataModel>
	suspend fun getNotifications(userId: String): Results<List<NotificationsModel>>
}

class MainRepositoryImpl(private val mainDataSource: MainDataSource) : MainRepository {

	override suspend fun getProfile(): Results<ProfileDataModel> {
		return mainDataSource.getProfile()
	}

	override suspend fun getNotifications(userId: String): Results<List<NotificationsModel>> {
		return mainDataSource.getNotifications(userId)
	}

}