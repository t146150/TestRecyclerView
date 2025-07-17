package com.example.testrecyclerview

import androidx.lifecycle.ViewModel

class ImageViewModel : ViewModel() {
    val imageUrls = mutableListOf<String>()

    fun setImages(urls: List<String>) {
        imageUrls.clear()
        imageUrls.addAll(urls)
    }

    fun addImage(url: String) {
        imageUrls.add(url)
    }
} 