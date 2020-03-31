package com.example.android_theerawuth_assignment.feature.main.presenter.list

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.android_theerawuth_assignment.feature.main.domain.NotificationInfo

class NotificationListAdapter :
		ListAdapter<NotificationInfo, NotificationViewHolder>(DIFF_COMPARATOR) {

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
		private val DIFF_COMPARATOR = object : DiffUtil.ItemCallback<NotificationInfo>() {
			override fun areItemsTheSame(oldItem: NotificationInfo,
			                             newItem: NotificationInfo): Boolean =
					oldItem == newItem

			override fun areContentsTheSame(oldItem: NotificationInfo,
			                                newItem: NotificationInfo): Boolean =
					oldItem == newItem
		}

	}
}