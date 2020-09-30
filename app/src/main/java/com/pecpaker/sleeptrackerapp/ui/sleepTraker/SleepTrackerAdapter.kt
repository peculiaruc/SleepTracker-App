package com.pecpaker.sleeptrackerapp.ui.sleepTraker

import android.content.res.Resources
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pecpaker.sleeptrackerapp.R
import com.pecpaker.sleeptrackerapp.Utli.convertDurationToFormatted
import com.pecpaker.sleeptrackerapp.Utli.convertNumericQualityToString
import com.pecpaker.sleeptrackerapp.dataSource.local.SleepNight

class SleepTrackerAdapter :
    androidx.recyclerview.widget.ListAdapter<SleepNight, SleepTrackerAdapter.ViewHolder>(
        SleepNightDiffCallback()
    ) {

    //ListAdapter takes care of this
//    var data = listOf<SleepNight>()
//        set(value) {
//            field = value
//            notifyDataSetChanged()
//        }


    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val sleepLength = itemView.findViewById<TextView>(R.id.sleep_length)
        val quality = itemView.findViewById<TextView>(R.id.quality_string)
        val qualityImage = itemView.findViewById<ImageView>(R.id.quality_image)

        fun bind(item: SleepNight) {
            val res = itemView.context.resources
            sleepLength.text =
                convertDurationToFormatted(item.startTimeMilli, item.endTimeMilli, res)
            quality.text = convertNumericQualityToString(item.sleepQuality, res)
            qualityImage.setImageResource(
                when (item.sleepQuality) {
                    0 -> R.drawable.ic_sleep_0
                    1 -> R.drawable.ic_sleep_1
                    2 -> R.drawable.ic_sleep_2
                    3 -> R.drawable.ic_sleep_3
                    4 -> R.drawable.ic_sleep_4
                    5 -> R.drawable.ic_sleep_5
                    else -> R.drawable.ic_sleep_active

                }
            )
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val itemView = layoutInflater.inflate(R.layout.list_sleep_night, parent, false)
                return ViewHolder(itemView)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }
    //ListAdapter takes care of this
//    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val item = data[position]
        val item = getItem(position)
        holder.bind(item)

    }

}

class SleepNightDiffCallback : DiffUtil.ItemCallback<SleepNight>() {
    override fun areItemsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
        return oldItem.nightId == newItem.nightId
    }

    override fun areContentsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
        return oldItem == newItem
    }

}



