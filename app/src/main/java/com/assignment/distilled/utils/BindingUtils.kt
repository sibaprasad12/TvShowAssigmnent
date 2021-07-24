package com.assignment.distilled.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.assignment.distilled.ui.adapter.MovieAdapter

/**
 * Created by Sibaprasad Mohanty on 30/06/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

class BindingUtils {
    companion object {
        @JvmStatic
        @BindingAdapter("adapter")
        fun setRecyclerViewAdapter(recyclerViewMovie: RecyclerView, adapter: MovieAdapter) {
            recyclerViewMovie.adapter = adapter
            recyclerViewMovie.layoutManager = LinearLayoutManager(recyclerViewMovie.context)
        }
    }
}