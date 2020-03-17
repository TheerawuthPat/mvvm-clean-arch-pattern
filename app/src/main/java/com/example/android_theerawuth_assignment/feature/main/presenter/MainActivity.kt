package com.example.android_theerawuth_assignment.feature.main.presenter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_theerawuth_assignment.R
import com.example.android_theerawuth_assignment.databinding.ActivityMainBinding
import com.example.android_theerawuth_assignment.feature.main.presenter.list.NotificationListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_try_again.*
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

class MainActivity : AppCompatActivity() {

	private val mainViewModel: MainViewModel by lifecycleScope.viewModel(this)
	private val notificationListAdapter: NotificationListAdapter by lifecycleScope.inject()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		val binding: ActivityMainBinding = DataBindingUtil.setContentView(this,
				R.layout.activity_main)
		binding.viewModel = mainViewModel
		binding.lifecycleOwner = this

		bindView()
		bindListener()
		getProfile()
	}

	private fun bindView() {
		rvNotification.apply {
			adapter = notificationListAdapter
			layoutManager = LinearLayoutManager(this@MainActivity)
		}

		swipeRefreshView.apply {
			setColorSchemeResources(R.color.colorPrimary)
			setOnRefreshListener {
				isRefreshing = false
				mainViewModel.reloadNotifications()
			}
		}

		notificationListAdapter.apply {
			registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
				override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
					if (positionStart == 0) {
						rvNotification.layoutManager?.scrollToPosition(0)
					}
				}
			})
		}
	}

	private fun bindListener() {
		tryAgainButton.setOnClickListener {
			mainViewModel.reloadNotifications()
		}
	}

	private fun getProfile() {
		mainViewModel.getProfile()
		mainViewModel.notificationDataSuccess.observe(this, Observer {
			notificationListAdapter.submitList(it)
		})
	}
}
