package com.example.lloydstask.wrapper

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

class GlideImageLoader(
    private val context: Context
) : ImageLoader {
    override fun loadImage(
        imageUrl: String,
        placeholderId: Int,
        imageView: ImageView
    ) {
        Glide.with(context).load(imageUrl).placeholder(placeholderId).into(imageView)
    }
}