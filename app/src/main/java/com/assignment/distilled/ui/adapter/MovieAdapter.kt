package com.assignment.distilled.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.assignment.distilled.BR
import com.assignment.distilled.controller.MovieClickListener
import com.assignment.distilled.data.TvShowData
import com.assignment.distilled.databinding.ItemviewTvshowBinding

/**
 * Created by Sibaprasad Mohanty on 21/07/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

class MovieAdapter(
    private val movieClickListener: MovieClickListener,
    private val isFromFavoriteTab: Boolean
) :
    RecyclerView.Adapter<MovieAdapter.PopularTvShowViewHolder>() {

    private var tvShowList: ArrayList<TvShowData> = ArrayList()

    class PopularTvShowViewHolder(private val binding: ItemviewTvshowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            obj: TvShowData,
            movieClickListener: MovieClickListener,
            isFromFavoriteTab: Boolean
        ) {
            binding.setVariable(BR.popularTvShow, obj)
            binding.setVariable(BR.listener, movieClickListener)
            binding.setVariable(BR.isNormalTvShowFragment, isFromFavoriteTab)
            binding.executePendingBindings()

            binding.root.setOnClickListener {
                movieClickListener.onTvShowClick(obj)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularTvShowViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val tvshowBinding: ItemviewTvshowBinding =
            ItemviewTvshowBinding.inflate(inflater, parent, false)
        return PopularTvShowViewHolder(tvshowBinding)
    }

    override fun onBindViewHolder(holder: PopularTvShowViewHolder, position: Int) {
        val item = differ.currentList[position]
        item.position = position
        holder.bind(item, movieClickListener, !isFromFavoriteTab)
    }

    override fun getItemCount() = differ.currentList.size

    private val TVSHOW_DIFF_CALLBACK: DiffUtil.ItemCallback<TvShowData> =
        object : DiffUtil.ItemCallback<TvShowData>() {
            override fun areItemsTheSame(
                @NonNull oldTvSHow: TvShowData,
                @NonNull newTvSHow: TvShowData
            ): Boolean {
                return oldTvSHow.name == newTvSHow.name
            }

            override fun areContentsTheSame(
                @NonNull oldTvSHow: TvShowData,
                @NonNull newTvSHow: TvShowData
            ): Boolean {
                return oldTvSHow.name == newTvSHow.name
            }
        }

    private val differ: AsyncListDiffer<TvShowData> =
        AsyncListDiffer(this, TVSHOW_DIFF_CALLBACK)

    fun setTvSHowList(tvShows: ArrayList<TvShowData>) {
        tvShowList = tvShows
        differ.submitList(tvShows)
    }
}