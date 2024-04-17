package com.example.archdatalayer.sleepTracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.archdatalayer.R
import com.example.archdatalayer.database.SleepDatabase
import com.example.archdatalayer.databinding.FragmentSleepTrackerBinding

/**
 * A simple [Fragment] subclass.
 * Use the [SleepTrackerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SleepTrackerFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        // default ways
        // return inflater.inflate(R.layout.fragment_sleep_tracker, container, false)

        // udacity ways
        val binding: FragmentSleepTrackerBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sleep_tracker, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = SleepDatabase.getInstance(application).sleepDatabaseDao
        val viewModelFactory = SleepTrackerViewModelFactory(dataSource, application)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(SleepTrackerViewModel::class.java)

        binding.lifecycleOwner = this
        binding.sleepTrackerViewModel = viewModel

        return binding.root
    }
}