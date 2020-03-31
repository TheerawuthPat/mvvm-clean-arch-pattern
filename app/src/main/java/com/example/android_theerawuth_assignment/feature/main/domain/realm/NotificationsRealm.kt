package com.example.android_theerawuth_assignment.feature.main.domain.realm

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class NotificationsRealm(
		@PrimaryKey
		var expire: Long = 0,
		var notifications: RealmList<NotificationInfoRealm> = RealmList()
) : RealmObject()