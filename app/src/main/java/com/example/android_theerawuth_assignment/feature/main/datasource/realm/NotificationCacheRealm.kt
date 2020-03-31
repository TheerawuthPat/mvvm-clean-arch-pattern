package com.example.android_theerawuth_assignment.feature.main.datasource.realm

import com.example.android_theerawuth_assignment.feature.main.domain.Notifications

class NotificationCacheRealm() : NotificationCache {
	override fun save(notifications: Notifications) {
	}

	override fun get(): Notifications {
		return Notifications()
	}

	override fun clear() {
	}
}