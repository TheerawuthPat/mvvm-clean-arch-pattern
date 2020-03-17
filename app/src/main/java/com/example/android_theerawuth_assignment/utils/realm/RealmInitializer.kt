package com.example.android_theerawuth_assignment.utils.realm

import android.content.Context
import io.realm.Realm

class RealmInitializer (private val context: Context) {
	fun init() {
		Realm.init(context)
	}
}