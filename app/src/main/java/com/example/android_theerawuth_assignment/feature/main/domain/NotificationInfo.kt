package com.example.android_theerawuth_assignment.feature.main.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NotificationInfo(
		@SerializedName("created")
		val created: String?,
		@SerializedName("text")
		val text: String?
): Parcelable