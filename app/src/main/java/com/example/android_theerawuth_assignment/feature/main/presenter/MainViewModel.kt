package com.example.android_theerawuth_assignment.feature.main.presenter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.android_theerawuth_assignment.application.core.BaseViewModel
import com.example.android_theerawuth_assignment.application.network.Results
import com.example.android_theerawuth_assignment.feature.main.domain.GetNotificationsUseCase
import com.example.android_theerawuth_assignment.feature.main.domain.GetProfileUseCase
import com.example.android_theerawuth_assignment.feature.main.domain.NotificationInfo
import com.example.android_theerawuth_assignment.feature.main.domain.UserModel
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel(private val getProfileUseCase: GetProfileUseCase,
                    private val getNotificationsUseCase: GetNotificationsUseCase) :
		BaseViewModel() {
	var userModel = UserModel()
	var isError: Boolean = false
	var isLoading: Boolean = false
		set(value) {
			field = value
			notifyChange()
		}

	val notificationDataSuccess = MutableLiveData<List<NotificationInfo>>()

	fun getProfile() {
		viewModelScope.launch {
			isLoading = true
			when (val results = getProfileUseCase.execute(Unit)) {
				is Results.Success -> {
					userModel.profileDataModel = results.data
					userModel.profileDataModel?.userId?.let {
						getNotifications(userId = it)
					} ?: run {
						processError()
					}
				}
				is Results.Network -> {
					processError()
					Timber.d("Network Error")
				}
				is Results.Timeout -> {
					processError()
					Timber.d("Network Timeout")
				}
				is Results.Error -> {
					processError()
					Timber.d("Error")
				}
				else -> {
					processError()
					Timber.d("Other Error")
				}
			}
		}
	}

	private suspend fun getNotifications(userId: String) {
		when (val results = getNotificationsUseCase.execute(userId)) {
			is Results.Success -> {
				processNotificationListSuccess(results.data)
			}
			is Results.Network -> {
				processError()
				Timber.d("Network Error")
			}
			is Results.Timeout -> {
				processError()
				Timber.d("Network Timeout")
			}
			is Results.Error -> {
				processError()
				Timber.d("Error")
			}
			else -> {
				processError()
				Timber.d("Other Error")
			}
		}
	}

	private fun processNotificationListSuccess(
			data: List<NotificationInfo>) {
		isLoading = false
		isError = false
		notificationDataSuccess.postValue(data)
		notifyChange()
	}

	private fun processError() {
		isLoading = false
		isError = true
		notifyChange()
	}

	fun reloadNotifications() {
		viewModelScope.launch {
			if (userModel.profileDataModel?.userId.isNullOrEmpty()) {
				getProfile()
			} else {
				userModel.profileDataModel?.userId?.let {
					getNotifications(it)
				}
			}
		}
	}

}