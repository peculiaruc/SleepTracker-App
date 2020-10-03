package com.pecpaker.sleeptrackerapp.ui.sleepDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.pecpaker.sleeptrackerapp.R
import com.pecpaker.sleeptrackerapp.dataSource.local.SleepDatabase
import com.pecpaker.sleeptrackerapp.databinding.FragmentSleepDetailBinding


/**
 * A simple [Fragment] subclass.
 * Use the [SleepDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SleepDetailFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentSleepDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_sleep_detail, container, false
        )

        val application = requireNotNull(this.activity).application
        val arguments = SleepDetailFragmentArgs.fromBundle(requireArguments())

        val dataSource = SleepDatabase.getInstance(application).sleepNightDao
        return binding.root
    }


}