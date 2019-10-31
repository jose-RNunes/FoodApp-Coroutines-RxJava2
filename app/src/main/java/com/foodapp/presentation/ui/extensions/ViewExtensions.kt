package com.foodapp.presentation.ui.extensions

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


/**
 * @param adapter
 * @param drawableItemDecoration drawable id to create divider
 * */
fun <VH : RecyclerView.ViewHolder> RecyclerView.verticalAdapter(
    adapter: RecyclerView.Adapter<VH>,
    drawableItemDecoration: Int? = null
) {
    this.adapter = adapter
    this.layoutManager = LinearLayoutManager(
        context,
        RecyclerView.VERTICAL,
        false
    )
    drawableItemDecoration?.let { drawable ->
        val itemDecoration = DividerItemDecoration(context, RecyclerView.VERTICAL).apply {
            ContextCompat.getDrawable(context, drawable)?.let { this.setDrawable(it) }
        }
        this.addItemDecoration(itemDecoration)
    }
}