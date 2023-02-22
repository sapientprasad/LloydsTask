package com.example.lloydstask.wrapper

import android.widget.ImageView

interface ImageLoader {
    fun loadImage(
        imageUrl: String,
        placeholderId: Int,
        imageView: ImageView
    )
}