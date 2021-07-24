package com.assignment.distilled.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.assignment.distilled.R
import com.assignment.distilled.databinding.FragmentLauncherBinding
import dagger.hilt.android.AndroidEntryPoint


/**
 * Created by Sibaprasad Mohanty on 22/07/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

@AndroidEntryPoint
class LauncherFragment : Fragment() {

    lateinit var binding: FragmentLauncherBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_launcher, container, false
        )
        val view: View = binding.root

        binding.buttonGo.setOnClickListener {
            val action =
                LauncherFragmentDirections.actionLauncherToHome()
            findNavController().navigate(action)
        }
        return view
    }

}