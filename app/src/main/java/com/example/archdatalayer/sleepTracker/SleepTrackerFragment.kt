package com.example.archdatalayer.sleepTracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.archdatalayer.R
import com.example.archdatalayer.database.SleepDatabase
import com.example.archdatalayer.databinding.FragmentSleepTrackerBinding
import timber.log.Timber

/**
 * responsibility:
 * - control UI
 * - navigation
 */
class SleepTrackerFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // default ways
        // return inflater.inflate(R.layout.fragment_sleep_tracker, container, false)

        // udacity ways
        val binding: FragmentSleepTrackerBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sleep_tracker, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = SleepDatabase.getInstance(application).sleepDatabaseDao
        val viewModelFactory = SleepTrackerViewModelFactory(dataSource, application)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(SleepTrackerViewModel::class.java)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.sleepTrackerViewModel = viewModel

        val adapter = SleepNightAdapter(SleepNightClickListener { night ->
            viewModel.onClickDetailSleepNight(night)
        })
        binding.sleepList.adapter = adapter

        viewModel.nights.observe(viewLifecycleOwner, Observer {
            Timber.i("viewModel.nights.observe")
            it?.let {
                adapter.submitList(it)
            }
        })

//        val manager: GridLayoutManager = GridLayoutManager(activity, 3)
//        binding.sleepList.layoutManager = manager

        /*
        * response to user event (stop button)
        * manage navigation to quality
        */
        viewModel.navigateToSleepQuality.observe(viewLifecycleOwner, Observer { night ->
            if (night !== null) {
                Timber.i("navigateToSleepQuality %d", night.nightId)
                this.findNavController().navigate(SleepTrackerFragmentDirections.actionSleepTrackerFragmentToSleepQualityFragment(night.nightId))
            }
        })

        // listen sleepDetail navigation instruction from viewModel
        viewModel.navigateToSleepDetailQuality.observe(viewLifecycleOwner) { night ->
            night?.let {
                Timber.i("navigateToSleepDetailQuality :: $night")
                this.findNavController().navigate(SleepTrackerFragmentDirections.actionSleepTrackerFragmentToSleepDetailFragment(night))
                viewModel.onSleepDetailNavigated()
            }
        }
        return binding.root
    }
}