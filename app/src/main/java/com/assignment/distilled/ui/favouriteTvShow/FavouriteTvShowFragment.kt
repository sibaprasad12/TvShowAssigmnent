package com.assignment.distilled.ui.favouriteTvShow

/**
 * Created by Sibaprasad Mohanty on 22/07/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.assignment.distilled.R
import com.assignment.distilled.controller.MovieClickListener
import com.assignment.distilled.data.TvShowData
import com.assignment.distilled.databinding.FragmentFavouriteTvshowBinding
import com.assignment.distilled.network.Status
import com.assignment.distilled.ui.adapter.MovieAdapter
import com.assignment.distilled.ui.detailsTvSHow.TvShowDetailsDialogFragment
import com.assignment.distilled.utils.LinearLayoutManagerWithSmoothScroller
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteTvShowFragment : Fragment(),
    SwipeRefreshLayout.OnRefreshListener, MovieClickListener {

    private val viewModel by viewModels<FavouriteTvShowViewModel>()
    lateinit var binding: FragmentFavouriteTvshowBinding
    private var isLoading: Boolean = false
    private val adapter: MovieAdapter by lazy {
        MovieAdapter(this, true)
    }
    private val layoutManager = LinearLayoutManagerWithSmoothScroller(activity)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_favourite_tvshow, container, false
        )
        val view: View = binding.root
        binding.viewModel = viewModel
        binding.movieAdapter = adapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserver()
        binding.recyclerViewFevTvShow.layoutManager = layoutManager
        initScrollListener()
    }


    private fun setObserver() {
        binding.swipeLayoutFev.setOnRefreshListener(this)
        viewModel.fetchTvSHowFromDatabaseByPage()

        viewModel.favouriteTvShowList.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.tvErrorFev.visibility = View.GONE
                    adapter.setTvSHowList(it.data as ArrayList<TvShowData>)
                    binding.swipeLayoutFev.isRefreshing = false
                }
                Status.ERROR -> {
                    Toast.makeText(
                        activity as FragmentActivity,
                        "Error ${it.message}",
                        Toast.LENGTH_LONG
                    ).show()
                    binding.swipeLayoutFev.isRefreshing = false
                }
            }
        })
    }

    override fun onRefresh() {
        viewModel.fetchTvSHowFromDatabaseByPage()
    }

    override fun onTvShowClick(tvShowData: TvShowData) {
        TvShowDetailsDialogFragment.newInstance(tvShowData).show(childFragmentManager, "TAG")
    }

    override fun onFevouriteClick(tvShowData: TvShowData) {
        viewModel.removeFavoriteMeteors(tvShowData)
    }

    private fun initScrollListener() {
        binding.recyclerViewFevTvShow.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() >= adapter.itemCount - 2) {
                        viewModel.fetchTvSHowFromDatabaseByPage()
                        isLoading = true
                    }
                }
            }
        })
    }

}