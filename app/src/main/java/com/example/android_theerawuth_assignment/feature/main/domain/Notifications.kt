package com.example.android_theerawuth_assignment.feature.main.domain


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Notifications(
		@SerializedName("notificationInfo")
		var notificationInfoList: List<NotificationInfo> = listOf(),
		@SerializedName("expire")
		var expire: Long = 0
) : Parcelable

fun Notifications.isExpired(currentTime: Long = System.currentTimeMillis()): Boolean {
	if (expire == 0L) {
		return true
	}
	return currentTime > expire
}
