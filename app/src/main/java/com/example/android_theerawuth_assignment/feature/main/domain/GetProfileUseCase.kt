package com.example.android_theerawuth_assignment.feature.main.domain

import com.example.android_theerawuth_assignment.application.core.UseCase
import com.example.android_theerawuth_assignment.application.network.Results
import com.example.android_theerawuth_assignment.feature.main.datasource.MainRepository

abstract class GetProfileUseCase : UseCase<Unit, Results<ProfileDataModel>>()

class GetProfileUseCaseImpl(private val repository: MainRepository) : GetProfileUseCase() {

	override suspend fun execute(request: Unit): Results<ProfileDataModel> {
		return repository.getProfile()
	}

}