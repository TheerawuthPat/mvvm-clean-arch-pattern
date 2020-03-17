package com.example.android_theerawuth_assignment.di

import com.example.android_theerawuth_assignment.di.module.networkModule
import com.example.android_theerawuth_assignment.feature.main.mainModule

var contributorModule = listOf(
		networkModule,
		mainModule
)