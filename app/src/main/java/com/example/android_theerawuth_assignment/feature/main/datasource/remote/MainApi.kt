package com.example.android_theerawuth_assignment.feature.main.datasource.remote

import com.example.android_theerawuth_assignment.feature.main.domain.NotificationsModel
import com.example.android_theerawuth_assignment.feature.main.domain.ProfileDataModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MainApi {
	@GET("user/profile")
	suspend fun getProfile(): Response<ProfileDataModel>

	@GET("users/{userId}/notifications")
	suspend fun getNotifications(@Path("userId") userId: String): Response<List<NotificationsModel>>
}