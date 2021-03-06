package com.pecpaker.sleeptrackerapp.ui.sleepTraker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.text.buildSpannedString
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.pecpaker.sleeptrackerapp.R
import com.pecpaker.sleeptrackerapp.dataSource.local.SleepDatabase
import com.pecpaker.sleeptrackerapp.databinding.FragmentSleepTrackerBinding


/**
 * A simple [Fragment] subclass.
 * Use the [SleepTrackerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SleepTrackerFragment : Fragment() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentSleepTrackerBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_sleep_tracker, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = SleepDatabase.getInstance(application).sleepNightDao

        val viewModelFactory = SleepTrackerViewModelFactory(dataSource, application)

        val sleepTrackerViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(SleepTrackerViewModel::class.java)

        binding.sleepTrackerViewModel = sleepTrackerViewModel

        //Implemting GridLayout
        val manager = GridLayoutManager(context, 3)
        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {

            override fun getSpanSize(position: Int) = when (position) {

                0 -> 3
                else -> 1
            }
        }
        binding.sleepList.layoutManager = manager

        val adapter = SleepTrackerAdapter(SleepNightListerner { nightId ->
            sleepTrackerViewModel.onSleepNightClicked(nightId)
        })

        binding.sleepList.adapter = adapter
        binding.setLifecycleOwner(this)

        sleepTrackerViewModel.navigateToSleepDataQuality.observe(
            viewLifecycleOwner,
            Observer { night ->
                night?.let {
                    this.findNavController().navigate(
                        SleepTrackerFragmentDirections
                            .actionSleepTrackerFragmentToSleepDetailFragment(night)
                    )
                    sleepTrackerViewModel.onSleepDataQualityNavigated()
                }
            })

        sleepTrackerViewModel.nights.observe(viewLifecycleOwner, Observer {
            it.let {
                adapter.addHeaderAndSubmitList(it)
            }
        })


        sleepTrackerViewModel.navigaToSleepNight.observe(viewLifecycleOwner, Observer { night ->
            night?.let {
                this.findNavController().navigate(
                    SleepTrackerFragmentDirections.actionSleepTrackerFragmentToSleepQualityFragment(
                        night.nightId
                    )
                )
                sleepTrackerViewModel.doneNavigetting()
            }
        })

        sleepTrackerViewModel.showSnackbarEvent.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                Snackbar.make(
                    requireActivity().findViewById(R.id.content),
                    getString(R.string.cleared_message),
                    Snackbar.LENGTH_SHORT
                ).show()
                sleepTrackerViewModel.doneShowingSnackbar()
            }
        })

        sleepTrackerViewModel
        return binding.root
    }


}
