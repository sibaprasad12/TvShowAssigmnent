package com.assignment.distilled.ui.tvShow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.assignment.distilled.R
import com.assignment.distilled.controller.MovieClickListener
import com.assignment.distilled.data.FilterData
import com.assignment.distilled.data.TvShowData
import com.assignment.distilled.databinding.FragmentTvshowBinding
import com.assignment.distilled.network.Status
import com.assignment.distilled.ui.MainActivity
import com.assignment.distilled.ui.adapter.MovieAdapter
import com.assignment.distilled.ui.detailsTvSHow.TvShowDetailsDialogFragment
import com.assignment.distilled.utils.AppConstant
import com.assignment.distilled.utils.LinearLayoutManagerWithSmoothScroller
import com.assignment.distilled.utils.NetworkUtil
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint


/**
 * create an instance of this fragment.
 */

@AndroidEntryPoint
class TvShowFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener, MovieClickListener {

    private val viewModel by viewModels<TvShowViewModel>()
    private var savedFilterID = 0
    private lateinit var binding: FragmentTvshowBinding
    private val adapter: MovieAdapter by lazy {
        MovieAdapter(this, false)
    }

    private val layoutManager = LinearLayoutManagerWithSmoothScroller(activity)

    private var listMeteors = ArrayList<TvShowData>()

    private var isLoading = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_tvshow, container, false
        )
        val view: View = binding.root
        binding.viewModel = viewModel
        binding.meteorAdapter = adapter
        binding.fabSort.setOnClickListener {
            showSortBottomSheetDialog()
        }
        binding.fabFilter.setOnClickListener {
            showFilterBottomSheetDialog()
        }
        binding.swipeLayout.setOnRefreshListener(this)
        setupUi()
        return view
    }

    private fun sortTvShow(filterParameter: Int) {
        listMeteors.clear()
        listMeteors.addAll(viewModel.sortTvShow(filterParameter))
        adapter.setTvSHowList(listMeteors)
        adapter.notifyDataSetChanged()
    }

    private fun filterTvShow(filterData: FilterData) {
        listMeteors.clear()
        listMeteors.addAll(viewModel.filterTvShow(filterData))
        if (listMeteors.isEmpty()) {
            binding.recyclerViewMeteor.visibility = View.GONE
        } else {
            binding.recyclerViewMeteor.visibility = View.VISIBLE
            adapter.setTvSHowList(listMeteors)
            adapter.notifyDataSetChanged()
        }
    }

    private fun showFilterBottomSheetDialog() {
        val bottomSheetDialog = BottomSheetDialog(activity as FragmentActivity)
        bottomSheetDialog.setContentView(R.layout.buttomsheet_filter)
        val radioGroupPopularity =
            bottomSheetDialog.findViewById<RadioGroup>(R.id.radioGroupPopularity)
        val radioGroupVoteAverage =
            bottomSheetDialog.findViewById<RadioGroup>(R.id.radioGroupVoteAverage)
        val radioGroupLanguage =
            bottomSheetDialog.findViewById<RadioGroup>(R.id.radioGroupLanguage)

        val buttonClear = bottomSheetDialog.findViewById<AppCompatButton>(R.id.buttonClear)
        val appCompatButtonApply = bottomSheetDialog.findViewById<AppCompatButton>(R.id.buttonApply)

        var popularity = 0
        var voteAverage = 0
        var language = ""

        radioGroupPopularity?.setOnCheckedChangeListener { _, checkedId ->
            popularity = when (checkedId) {
                R.id.radioButtonHighPopularity -> AppConstant.FILTER_POPULARITY_HIGH
                R.id.radioButtonMidPopularity -> AppConstant.FILTER_POPULARITY_MED
                R.id.radioButtonLowPopularity -> AppConstant.FILTER_POPULARITY_LOW
                else -> {
                    AppConstant.DEFAULT
                }
            }
        }

        radioGroupVoteAverage?.setOnCheckedChangeListener { _, checkedId ->
            voteAverage = when (checkedId) {
                R.id.radioButtonHighVote -> AppConstant.FILTER_VOTE_HIGH
                R.id.radioButtonMidVote -> AppConstant.FILTER_VOTE_MID
                R.id.radioButtonLowVote -> AppConstant.FILTER_VOTE_LOW
                else -> {
                    AppConstant.DEFAULT
                }
            }
        }

        radioGroupLanguage?.setOnCheckedChangeListener { _, checkedId ->
            language = when (checkedId) {
                R.id.radioButtonEnglish -> AppConstant.FILTER_LANGUAGE_EN
                R.id.radioButtonJapaneese -> AppConstant.FILTER_LANGUAGE_JA
                R.id.radioButtonOthers -> AppConstant.FILTER_LANGUAGE_OTHER
                else -> {
                    ""
                }
            }
        }
        appCompatButtonApply?.setOnClickListener {
            filterTvShow(FilterData(language, voteAverage, popularity))
            bottomSheetDialog.dismiss()
        }
        buttonClear?.setOnClickListener {
            radioGroupPopularity?.clearCheck()
            radioGroupVoteAverage?.clearCheck()
            radioGroupLanguage?.clearCheck()
            popularity = 0
            voteAverage = 0
            language = ""
            fetchPopularTvShows(true)
            bottomSheetDialog.dismiss()
        }
        bottomSheetDialog.show()
    }

    private fun showSortBottomSheetDialog() {
        val bottomSheetDialog = BottomSheetDialog(activity as FragmentActivity)
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_sort)
        val radioGroupFilter = bottomSheetDialog.findViewById<RadioGroup>(R.id.radioGroupFilter)
        val buttonSort = bottomSheetDialog.findViewById<AppCompatButton>(R.id.buttonSort)
        if (savedFilterID != 0) {
            radioGroupFilter?.check(savedFilterID)
        }
        var filterType = 0
        radioGroupFilter?.setOnCheckedChangeListener { _, checkedId ->
            savedFilterID = checkedId
            filterType = when (checkedId) {
                R.id.radioButtonPopularity -> AppConstant.SORT_BY_POPULARITY
                R.id.radioButtonName -> AppConstant.SORT_BY_NAME
                R.id.radioButtonFirstAirDate -> AppConstant.SORT_AIR_DATE
                R.id.radioButtonVote -> AppConstant.SORT_VOTE_AVERAGE
                R.id.radioButtonVoteCount -> AppConstant.SORT_VOTE_COUNT
                else -> {
                    AppConstant.DEFAULT
                }
            }
        }
        buttonSort?.setOnClickListener {
            sortTvShow(filterType)
            bottomSheetDialog.dismiss()
        }
        bottomSheetDialog.show()
    }

    override fun onRefresh() {
        savedFilterID = 0
        if (NetworkUtil.isAvailable(activity as FragmentActivity)) {
            fetchPopularTvShows(true)
        } else {
            fetchFromDatabase()
        }
    }

    private fun setupUi() {
        binding.recyclerViewMeteor.layoutManager = layoutManager
        initScrollListener()
        if (NetworkUtil.isAvailable(activity as FragmentActivity)) {
            fetchPopularTvShows()
        } else {
            (activity as MainActivity).showMessageOKCancel(
                "No Internet Connection"
            )
            { _, _ ->

            }
        }
    }

    private fun fetchPopularTvShows(isRefresh: Boolean = false) {
        viewModel.getTvShows(isRefresh).observe(viewLifecycleOwner, {
            it.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.recyclerViewMeteor.visibility = View.VISIBLE
                        adapter.setTvSHowList(resource.data as ArrayList<TvShowData>)
                        adapter.notifyDataSetChanged()
                        isLoading = false
                        binding.swipeLayout.isRefreshing = false
                    }
                    Status.ERROR -> {
                        Toast.makeText(
                            activity as FragmentActivity,
                            "Error ${resource.message}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        })
    }

    private fun fetchFromDatabase() {
        viewModel.getTvSHowsFromDatabase().observe(viewLifecycleOwner, {
            it.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        adapter.setTvSHowList(resource.data as ArrayList<TvShowData>)
                        adapter.notifyDataSetChanged()
                        isLoading = false
                        binding.swipeLayout.isRefreshing = false
                    }
                }
            }
        })
    }

    private fun initScrollListener() {
        binding.recyclerViewMeteor.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() >= adapter.itemCount - 2) {
                        fetchPopularTvShows()
                        isLoading = true
                    }
                }
            }
        })
    }

    override fun onTvShowClick(tvShowData: TvShowData) {
        TvShowDetailsDialogFragment.newInstance(tvShowData).show(childFragmentManager, "")
    }

    override fun onFevouriteClick(tvShowData: TvShowData) {
        viewModel.insertFavoriteMeteors(tvShowData)
        tvShowData.isFavourite = if (tvShowData.isFavourite > 0) 1 else 0
        adapter.notifyItemChanged(tvShowData.position, tvShowData)
    }
}