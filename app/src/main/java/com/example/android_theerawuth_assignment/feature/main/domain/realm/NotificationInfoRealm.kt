package com.example.android_theerawuth_assignment.feature.main.domain.realm

import io.realm.RealmObject

open class NotificationInfoRealm(
		var created: String = "",
		var text: String = ""
) : RealmObject()