package com.example.testrecyclerview

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import kotlin.concurrent.thread
import java.util.UUID

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var btnAdd: Button
    private lateinit var btnReload: Button
    private lateinit var pageAdapter: PageAdapter

    private val imageViewModel: ImageViewModel by viewModels()
    private val pageSize = 70
    private val pageReloadSize = 140
    private val imageUrl = "https://picsum.photos/200/200?random="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.viewPager)
        btnAdd = findViewById(R.id.btn_add)
        btnReload = findViewById(R.id.btn_reload)

        pageAdapter = PageAdapter(imageViewModel.imageUrls.chunked(pageSize))
        viewPager.adapter = pageAdapter

        btnAdd.setOnClickListener {
            addImage()
        }
        btnReload.setOnClickListener {
            reloadAll()
        }

        if (imageViewModel.imageUrls.isEmpty()) {
            Log.d("hieudm1","")
            reloadAll()
        }
    }

    private fun getRandomImageUrl(): String {
        return "$imageUrl${UUID.randomUUID()}"
    }

    private fun addImage() {
        val url = getRandomImageUrl()
        imageViewModel.addImage(url)
        val oldPageCount = pageAdapter.pages.size
        val newPages = imageViewModel.imageUrls.chunked(pageSize)
        pageAdapter.updatePagesWithNotify(newPages, oldPageCount)
        viewPager.setCurrentItem((imageViewModel.imageUrls.size - 1) / pageSize, true)
    }

    private fun reloadAll() {
        val newUrls = List(pageReloadSize) { getRandomImageUrl() }
        imageViewModel.setImages(newUrls)
        pageAdapter.updatePages(imageViewModel.imageUrls.chunked(pageSize))
        viewPager.setCurrentItem(0, false)
    }
}