package com.assignment.distilled.ui.detailsTvSHow


import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.assignment.distilled.R
import com.assignment.distilled.data.TvShowData
import com.assignment.distilled.databinding.FragmentTvshowDetailsBinding


/**
 * Created by Sibaprasad Mohanty on 22/07/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */


class TvShowDetailsDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentTvshowDetailsBinding

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            dialog.window!!.setWindowAnimations(
                R.style.styleDialogFragment
            )
            dialog.window!!.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_tvshow_details, container, false
        )
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tvShowData = arguments?.getParcelable<TvShowData>(TVSHOW_DATA)
            ?: throw IllegalStateException("No args provided")
        binding.popularTvShow = tvShowData
        binding.imageViewBack.setOnClickListener {
            dismissAllowingStateLoss()
        }
    }

    companion object {
        val TVSHOW_DATA = "TvSHowDetails"
        fun newInstance(
            item: TvShowData
        ): TvShowDetailsDialogFragment = TvShowDetailsDialogFragment().apply {
            arguments = Bundle().apply {
                putParcelable(TVSHOW_DATA, item)
            }
        }
    }
}