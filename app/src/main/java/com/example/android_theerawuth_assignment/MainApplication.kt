package com.example.android_theerawuth_assignment

import android.app.Application
import com.example.android_theerawuth_assignment.debugtools.DebugTools
import com.example.android_theerawuth_assignment.di.contributorModule
import com.example.android_theerawuth_assignment.utils.realm.RealmInitializer
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class MainApplication : Application() {

	override fun onCreate() {
		super.onCreate()
		setupKoin()
		DebugTools.init(this)
		RealmInitializer(this@MainApplication).init()
	}

	private fun setupKoin() {
		startKoin {
			androidContext(this@MainApplication)
			modules(contributorModule)
			androidLogger()
		}
	}
}