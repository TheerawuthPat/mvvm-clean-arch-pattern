package com.example.android_theerawuth_assignment.feature.main.presenter.list

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.android_theerawuth_assignment.feature.main.domain.NotificationsModel

class NotificationListAdapter :
		ListAdapter<NotificationsModel, NotificationViewHolder>(DIFF_COMPARATOR) {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
		return NotificationViewHolder.newInstance(parent)
	}

	override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
		if(position % 2 == 1) {
			holder.bind(getItem(position), true)
		} else {
			holder.bind(getItem(position), false)
		}
	}

	companion object {
		private val DIFF_COMPARATOR = object : DiffUtil.ItemCallback<NotificationsModel>() {
			override fun areItemsTheSame(oldItem: NotificationsModel,
			                             newItem: NotificationsModel): Boolean =
					oldItem == newItem

			override fun areContentsTheSame(oldItem: NotificationsModel,
			                                newItem: NotificationsModel): Boolean =
					oldItem == newItem
		}

	}
}