package com.example.android_theerawuth_assignment.application.core

abstract class UseCase<P, R> {
	abstract suspend fun execute(request: P): R
}