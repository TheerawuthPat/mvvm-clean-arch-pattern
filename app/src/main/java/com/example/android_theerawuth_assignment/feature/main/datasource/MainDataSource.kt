package com.example.android_theerawuth_assignment.feature.main.datasource

import com.example.android_theerawuth_assignment.application.network.Results
import com.example.android_theerawuth_assignment.feature.main.domain.NotificationInfo
import com.example.android_theerawuth_assignment.feature.main.domain.ProfileDataModel

interface MainDataSource {
	suspend fun getProfile(): Results<ProfileDataModel>
	suspend fun getNotifications(userId: String): Results<List<NotificationInfo>>
}