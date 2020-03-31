package com.example.android_theerawuth_assignment.feature.main.datasource.realm

import com.example.android_theerawuth_assignment.feature.main.domain.Notifications

interface NotificationCache {
	fun save(notifications: Notifications)
	fun get(): Notifications
	fun clear()
}