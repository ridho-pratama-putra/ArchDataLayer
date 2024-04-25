package com.example.archdatalayer.sleepTracker

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.archdatalayer.database.SleepNight
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

/**
 * recycle views use it to figure out which view holders are different
 */
private const val ITEM_VIEW_TYPE_HEADER = 0
private const val ITEM_VIEW_TYPE_ITEM = 1
/*
* this class responsible to adapt data tobe used by recycle view
 */
//class SleepNightAdapter: RecyclerView.Adapter<ViewHolder>() {
// listAdapter : chatGPT -> mengoptimalkan kinerja aplikasi Anda dengan menghindari pembaruan yang tidak perlu pada item yang tidak berubah.
class SleepNightAdapter(val sleepNightClickListener: SleepNightClickListener): ListAdapter<DataItem, RecyclerView.ViewHolder>(SleepNightDiffCallback()) {

    private val adapterScope = CoroutineScope(Dispatchers.Default)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            ITEM_VIEW_TYPE_HEADER -> TextViewHolder.from(parent)
            ITEM_VIEW_TYPE_ITEM -> ViewHolder.from(parent)
            else -> throw ClassCastException("Unknown type $viewType")
        }
    }

    fun addHeaderAndSubmitList(list: List<SleepNight>?) {
        adapterScope.launch {
            val items = when(list) {
                null -> listOf(DataItem.Header)
                else -> listOf(DataItem.Header) + list.map { DataItem.SleepNightItem(it) }
            }

            withContext(Dispatchers.Main) {
                submitList(items)
            }
        }
    }

    /*
    * called everytime view recycled from previous screen
    */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is ViewHolder -> {
                /**
                 *  what is this???? how it can be happen, like sleep night item is also sleep night too??
                 *  answer:
                 *
                 */
                val item = getItem(position) as DataItem.SleepNightItem
                holder.bind(item.sleepNightInstance, sleepNightClickListener)
                Timber.i("ViewHolder onBindViewHolder $this $holder")
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item: DataItem = getItem(position)
        return when(item) {
            is DataItem.Header -> ITEM_VIEW_TYPE_HEADER
            is DataItem.SleepNightItem -> ITEM_VIEW_TYPE_ITEM
        }
    }
}