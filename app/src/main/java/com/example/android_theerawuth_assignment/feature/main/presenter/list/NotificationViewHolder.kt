package com.example.android_theerawuth_assignment.feature.main.presenter.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.android_theerawuth_assignment.R
import com.example.android_theerawuth_assignment.databinding.ItemNotificationListBinding
import com.example.android_theerawuth_assignment.feature.main.domain.NotificationInfo

class NotificationViewHolder(
		private val binding: ItemNotificationListBinding
) : RecyclerView.ViewHolder(binding.root) {

	fun bind(notification: NotificationInfo, isBackgroundChange: Boolean) {
		binding.data = notification
		binding.itemNotificationLayout.let {
			if (isBackgroundChange) {
				it.setBackgroundColor(ContextCompat.getColor(it.context, R.color.gray_light))
			} else {
				it.setBackgroundColor(ContextCompat.getColor(it.context, R.color.white))
			}
		}
		binding.executePendingBindings()
	}

	companion object {
		fun newInstance(parent: ViewGroup): NotificationViewHolder {
			return NotificationViewHolder(
					ItemNotificationListBinding.inflate(LayoutInflater.from(parent.context), parent,
							false))
		}
	}
}