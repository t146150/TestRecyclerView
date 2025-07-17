package com.example.testrecyclerview

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation

class ImageAdapter(private val images: List<String>, context: Context) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {
    private val radiusPx = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, 7f, context.resources.displayMetrics
    )
    class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageView)
        val progressBar: ProgressBar = view.findViewById(R.id.progressBar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        return ImageViewHolder(view)
    }

    override fun getItemCount() = images.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.progressBar.visibility = View.VISIBLE

        holder.imageView.load(images[position]) {
            transformations(RoundedCornersTransformation(radiusPx))
            listener(
                onSuccess = { _, _ -> holder.progressBar.visibility = View.GONE },
                onError = { _, _ -> holder.progressBar.visibility = View.GONE }
            )
        }
    }
} 