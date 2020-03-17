package com.example.android_theerawuth_assignment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.MockKAnnotations
import org.junit.Before
import org.junit.Rule

abstract class BaseTest {

	// Executes tasks in the Architecture Components in the same thread
	@get:Rule
	var instantTaskExecutorRule = InstantTaskExecutorRule()

	@Before
	open fun setUp() {
		MockKAnnotations.init(this, relaxUnitFun = true)
	}
}