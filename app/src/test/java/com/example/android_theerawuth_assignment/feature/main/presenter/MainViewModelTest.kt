package com.example.android_theerawuth_assignment.feature.main.presenter

import com.example.android_theerawuth_assignment.ViewModelTest
import com.example.android_theerawuth_assignment.application.network.Results
import com.example.android_theerawuth_assignment.feature.main.domain.GetNotificationsUseCase
import com.example.android_theerawuth_assignment.feature.main.domain.GetProfileUseCase
import com.example.android_theerawuth_assignment.feature.main.domain.NotificationsModel
import com.example.android_theerawuth_assignment.feature.main.domain.ProfileDataModel
import com.example.android_theerawuth_assignment.livedata.observeOnce
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Test

class MainViewModelTest : ViewModelTest() {

	@MockK
	private lateinit var getProfileUseCase: GetProfileUseCase

	@MockK
	private lateinit var getNotificationsUseCase: GetNotificationsUseCase

	private val viewModel by lazy { MainViewModel(getProfileUseCase, getNotificationsUseCase) }

	@Test
	fun getProfile_ShouldReturnNotificationList_WhenGetProfileSuccessAndNotificationListSuccess() =
			runBlocking {
				val profileResults = Results.Success(ProfileDataModel(userId = "user123"))
				val userId = Results.Success(ProfileDataModel(userId = "user123"))
						.data.userId
				val notificationListResults = Results.Success(listOf(
						NotificationsModel(text = "I Like Post You", created = "12/12/12")
				))
				coEvery { getProfileUseCase.execute(Unit) } returns profileResults
				coEvery {
					userId?.let {
						getNotificationsUseCase.execute(it)
					}
				} returns notificationListResults

				viewModel.getProfile()

				viewModel.notificationDataSuccess.observeOnce {
					Truth.assertThat(Results.Success(it))
							.isEqualTo(notificationListResults)
				}
			}

	@Test
	fun getProfile_ShouldReturnError_WhenGetProfileSuccessButUserIdIsNull() =
			runBlocking {
				val profileResults = Results.Success(ProfileDataModel(userId = null))
				coEvery { getProfileUseCase.execute(Unit) } returns profileResults

				viewModel.getProfile()

				Truth.assertThat(viewModel.userModel.profileDataModel?.userId)
						.isNull()
			}

	@Test
	fun getProfile_ShouldReturnFailure_WhenGetProfileSuccessButGetNotificationListFailure() =
			runBlocking {
				val profileResults = Results.Success(ProfileDataModel(userId = "user123"))
				val userId = Results.Success(ProfileDataModel(userId = "user123"))
						.data.userId
				val networkResults = Results.Network
				viewModel.isError = true
				coEvery { getProfileUseCase.execute(Unit) } returns profileResults
				coEvery {
					userId?.let {
						getNotificationsUseCase.execute(it)
					}
				} returns networkResults

				viewModel.getProfile()

				Truth.assertThat(viewModel.isError)
						.isTrue()
			}

	@Test
	fun getProfile_ShouldReturnFailure_WhenGetProfileFailure() =
			runBlocking {
				val networkError = Results.Network
				viewModel.isError = true
				coEvery { getProfileUseCase.execute(Unit) } returns networkError

				viewModel.getProfile()

				Truth.assertThat(viewModel.isError)
						.isTrue()
			}

}