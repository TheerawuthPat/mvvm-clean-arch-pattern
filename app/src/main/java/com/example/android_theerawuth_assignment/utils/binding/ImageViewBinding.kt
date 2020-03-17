package com.example.android_theerawuth_assignment.utils.binding

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.android_theerawuth_assignment.R

class ImageViewBinding {
	companion object {
		@JvmStatic
		@BindingAdapter(
				"imageUrl"
		)
		fun bindImageUrl(imageView: AppCompatImageView, url: String?) {
			imageView.let {
				url?.let { url ->
					Glide.with(it.context)
							.load(url)
							.asBitmap()
							.placeholder(R.drawable.ic_place_holder_profile)
							.into(it)
				}
			}
		}
	}
}

