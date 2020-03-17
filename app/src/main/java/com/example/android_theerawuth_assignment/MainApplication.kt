package com.example.android_theerawuth_assignment

import android.app.Application
import com.example.android_theerawuth_assignment.di.contributorModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class MainApplication : Application() {

	override fun onCreate() {
		super.onCreate()
		setupKoin()
		Timber.plant(Timber.DebugTree())
	}

	private fun setupKoin() {
		startKoin {
			androidContext(this@MainApplication)
			modules(contributorModule)
			androidLogger()
		}
	}
}