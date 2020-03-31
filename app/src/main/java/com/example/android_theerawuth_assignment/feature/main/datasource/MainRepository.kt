package com.example.android_theerawuth_assignment.feature.main.datasource

import com.example.android_theerawuth_assignment.application.network.Results
import com.example.android_theerawuth_assignment.feature.main.datasource.realm.NotificationCache
import com.example.android_theerawuth_assignment.feature.main.domain.NotificationInfo
import com.example.android_theerawuth_assignment.feature.main.domain.ProfileDataModel

interface MainRepository {
	suspend fun getProfile(): Results<ProfileDataModel>
	suspend fun getNotifications(userId: String): Results<List<NotificationInfo>>
}

class MainRepositoryImpl(private val mainDataSource: MainDataSource,
                         private val notificationCache: NotificationCache) : MainRepository {

	override suspend fun getProfile(): Results<ProfileDataModel> {
		return mainDataSource.getProfile()
	}

	private suspend fun getNotificationListFromApi(
			userId: String): Results<List<NotificationInfo>> {
		return mainDataSource.getNotifications(userId)
//				.also {
//					if (it is Results.Success) {
//						notificationCache.save(it.data)
//					}
//				}
	}

	override suspend fun getNotifications(userId: String): Results<List<NotificationInfo>> {
		return getNotificationListFromApi(userId)
//		if (notificationCache.get()
//						.isExpired()) {
//			getNotificationListFromApi(userId)
//		}
//		} else {
//			return Results.Success(notificationCache.get())
//		}
	}

}