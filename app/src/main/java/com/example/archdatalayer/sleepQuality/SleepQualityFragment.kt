package com.example.archdatalayer.sleepQuality

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.archdatalayer.R
import com.example.archdatalayer.database.SleepDatabase
import com.example.archdatalayer.databinding.FragmentSleepQualityBinding
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 * Use the [SleepQualityFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SleepQualityFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentSleepQualityBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_sleep_quality, container, false
        )

        val application = requireNotNull(this.activity).application
        val arguments = SleepQualityFragmentArgs.fromBundle(requireArguments())
        val dataSource = SleepDatabase.getInstance(application).sleepDatabaseDao
        val viewModelFactory = SleepQualityViewModelFactory(arguments.sleepNightKey, dataSource)
        val sleepQualityViewModel =
            ViewModelProvider(this, viewModelFactory).get(SleepQualityViewModel::class.java)

        binding.sleepQualityViewModel = sleepQualityViewModel

        sleepQualityViewModel.navigateToSleepTracker.observe(viewLifecycleOwner, Observer {
            Timber.i("navigateToSleepTracker with it = $it")
            if (it == true) {
                this.findNavController().navigate(SleepQualityFragmentDirections.actionSleepQualityFragmentToSleepTrackerFragment())
                sleepQualityViewModel.doneNavigatingToSleepTracker()
            }
        })

        return binding.root
    }
}