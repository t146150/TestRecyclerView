package com.example.testrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PageAdapter(var pages: List<List<String>>) : RecyclerView.Adapter<PageAdapter.PageViewHolder>() {

    class PageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.page_recycler, parent, false)
        return PageViewHolder(view)
    }

    override fun getItemCount() = pages.size

    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {
        holder.recyclerView.layoutManager = GridLayoutManager(holder.recyclerView.context, 7)
        holder.recyclerView.adapter = ImageAdapter(pages[position], holder.recyclerView.context)
        if (holder.recyclerView.itemDecorationCount == 0) {
            holder.recyclerView.addItemDecoration(SpacingItemDecoration(7, 2))
        }
    }

    fun updatePages(newPages: List<List<String>>) {
        this.pages = newPages
        notifyDataSetChanged()
    }
} 