package com.example.android_theerawuth_assignment.debugtools

import android.content.Context
import com.facebook.stetho.Stetho
import timber.log.Timber

object DebugTools {
	fun init(context: Context) {
		initLogging()
		initStethoNetwork(context)
	}

	private fun initStethoNetwork(context: Context) {
		Stetho.initialize(Stetho.newInitializerBuilder(context)
				.enableDumpapp(Stetho.defaultDumperPluginsProvider(context))
				.enableWebKitInspector(Stetho.defaultInspectorModulesProvider(context))
				.build())
	}

	private fun initLogging() {
		Timber.plant(Timber.DebugTree())
	}
}