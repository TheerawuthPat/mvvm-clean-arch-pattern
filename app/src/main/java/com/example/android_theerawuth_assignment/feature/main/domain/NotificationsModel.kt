package com.example.android_theerawuth_assignment.feature.main.domain


import com.google.gson.annotations.SerializedName

data class NotificationsModel(
    @SerializedName("created")
    val created: String?,
    @SerializedName("text")
    val text: String?
)