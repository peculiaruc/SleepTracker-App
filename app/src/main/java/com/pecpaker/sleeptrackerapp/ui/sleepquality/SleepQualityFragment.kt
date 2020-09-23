package com.pecpaker.sleeptrackerapp.ui.sleepquality

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.pecpaker.sleeptrackerapp.R
import com.pecpaker.sleeptrackerapp.dataSource.local.SleepDatabase
import com.pecpaker.sleeptrackerapp.databinding.FragmentSleepQualityBinding


/**
 * A simple [Fragment] subclass.
 * Use the [SleepQualityFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SleepQualityFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentSleepQualityBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_sleep_quality, container, false)

        val application = requireNotNull(this.activity).application

        val arguments = SleepQualityFragmentArgs.fromBundle(arguments!!)

        val dataSource = SleepDatabase.getInstance(application).sleepNightDao

        val viewModelFacroty = SleepQualityViewModelFacroty(arguments.sleepNightKey, dataSource)

        val SleepQualityViewModel =
            ViewModelProviders.of(this, viewModelFacroty).get(SleepQualityViewModel::class.java)

        binding.sleepQualityViewModel = sleepQualityViewModel

        sleepQualityViewModel.navigateToSleepTracker.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                this.findNavController().navigate(
                    R.id.action_sleepQualityFragment_to_sleepTrackerFragment
                )
                sleepQualityViewModel.doneNavigating()
            }
        })


        return binding.root
    }

}