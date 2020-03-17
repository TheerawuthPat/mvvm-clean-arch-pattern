package com.example.android_theerawuth_assignment

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before

abstract class ViewModelTest : BaseTest() {

	private val mainThreadSurrogate = newSingleThreadContext("UI thread")

	@Before
	override fun setUp() {
		super.setUp()
		Dispatchers.setMain(mainThreadSurrogate)
	}

	@After
	fun tearDown() {
		mainThreadSurrogate.close()
	}
}