package com.assignment.distilled.utils

import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.assignment.distilled.BuildConfig
import com.assignment.distilled.R
import com.assignment.distilled.ui.adapter.MovieAdapter
import com.squareup.picasso.Picasso


/**
 * Created by Sibaprasad Mohanty on 22/07/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */


class BindingAdapters {
    companion object {

        @JvmStatic
        @BindingAdapter("tvShowImage")
        fun loadImage(imageview: AppCompatImageView, imageUrl: String) {

            val fullImageUrl = BuildConfig.BASE_IMAGE_URL + imageUrl
            if(!imageUrl.isNullOrEmpty())
            Picasso.get()
                .load(fullImageUrl)
                .into(imageview)
        }

        @JvmStatic
        @BindingAdapter("visibleGone")
        fun showOrHideProgress(view: View, showHideStatus: Boolean) {
            view.visibility = if (showHideStatus) View.VISIBLE else View.GONE
        }

        @JvmStatic
        @BindingAdapter("errorVisibleGone")
        fun showOrHideError(view: View, error: String) {
            view.visibility = if (error.isEmpty()) View.GONE else View.VISIBLE
        }

        @JvmStatic
        @BindingAdapter("errorMessage")
        fun showErrorMessage(view: AppCompatTextView, message: String) {
            message.let {
                view.text = message
            }
        }

        @JvmStatic
        @set:BindingAdapter("invisible")
        var View.invisible
            get() = visibility == View.INVISIBLE
            set(value) {
                visibility = if (value) View.INVISIBLE else View.VISIBLE
            }

        @JvmStatic
        @set:BindingAdapter("gone")
        var View.gone
            get() = visibility == View.GONE
            set(value) {
                visibility = if (value) View.GONE else View.VISIBLE
            }

        @JvmStatic
        @BindingAdapter("adapter")
        fun setAdapter(view: RecyclerView, adapter: MovieAdapter) {
            view.adapter = adapter
            (view.adapter as? MovieAdapter)?.notifyDataSetChanged()
        }

        @JvmStatic
        @BindingAdapter("weatherDescription")
        fun showWeatherDescription(view: AppCompatTextView, message: String) {
            message.let {
                view.text = message
            }
        }

        @JvmStatic
        @BindingAdapter("cardBackground")
        fun setCardBackground(cardview: CardView, weather: String) {
            when (weather) {
                "Rain" -> {
                    cardview.setBackgroundColor(
                        ContextCompat.getColor(
                            cardview.context,
                            R.color.white
                        )
                    )
                }
                "Clouds" -> {
                    cardview.setBackgroundColor(
                        ContextCompat.getColor(
                            cardview.context,
                            R.color.color_d3d3d3
                        )
                    )
                }
                else -> {
                    cardview.setBackgroundColor(
                        ContextCompat.getColor(
                            cardview.context,
                            R.color.color_d3d3d3
                        )
                    )
                }
            }
        }

        @JvmStatic
        @BindingAdapter("textChangedListener")
        fun bindTextWatcher(editText: EditText, textWatcher: TextWatcher) {
            editText.addTextChangedListener(textWatcher)
        }

        @JvmStatic
        @BindingAdapter("onRefreshListener")
        fun setSwipeRefreshLayout(
            swipeRefreshLayout: SwipeRefreshLayout,
            isRefreshing: Boolean
        ) {
            swipeRefreshLayout.isRefreshing = isRefreshing
        }
    }
}