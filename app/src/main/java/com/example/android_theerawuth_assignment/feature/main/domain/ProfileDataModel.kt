package com.example.android_theerawuth_assignment.feature.main.domain


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProfileDataModel(
    @SerializedName("avatar")
    val avatar: String? = "",
    @SerializedName("firstName")
    val firstName: String? = "",
    @SerializedName("followers")
    val followers: Int? = 0,
    @SerializedName("following")
    val following: Int? = 0,
    @SerializedName("lastName")
    val lastName: String? = "",
    @SerializedName("likes")
    val likes: Int? = 0,
    @SerializedName("userId")
    val userId: String? = ""
) : Parcelable {
    val likeToDisplay get() = likes?.toString()
    val followersToDisplay get() = followers?.toString()
    val followingToDisplay get() = following?.toString()
    val fullName get() = "$firstName $lastName"
}

