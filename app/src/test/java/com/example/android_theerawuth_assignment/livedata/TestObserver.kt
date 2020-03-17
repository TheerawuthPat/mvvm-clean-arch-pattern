package com.example.android_theerawuth_assignment.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

class TestObserver<T> : Observer<T> {

	val observedValues = mutableListOf<T?>()

	override fun onChanged(value: T?) {
		observedValues.add(value)
	}
}

fun <T> LiveData<T>.testObserver() = TestObserver<T>().also {
	observeForever(it)
}