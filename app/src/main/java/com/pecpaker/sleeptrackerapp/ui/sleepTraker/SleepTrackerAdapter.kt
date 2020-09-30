package com.pecpaker.sleeptrackerapp.ui.sleepTraker

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pecpaker.sleeptrackerapp.R
import com.pecpaker.sleeptrackerapp.Utli.convertDurationToFormatted
import com.pecpaker.sleeptrackerapp.Utli.convertNumericQualityToString
import com.pecpaker.sleeptrackerapp.dataSource.local.SleepNight
import kotlinx.android.synthetic.main.fragment_sleep_quality.view.*

class SleepTrackerAdapter : RecyclerView.Adapter<SleepTrackerAdapter.ViewHolder>() {

    var data = listOf<SleepNight>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // val textView = itemView.findViewById<TextView>(R.id.text_View)
        val sleepLength = itemView.findViewById<TextView>(R.id.sleep_length)
        val quality = itemView.findViewById<TextView>(R.id.quality_string)
        val qualityImage = itemView.findViewById<ImageView>(R.id.quality_image)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.list_sleep_night, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        val res = holder.itemView.context.resources
        holder.sleepLength.text =
            convertDurationToFormatted(item.startTimeMilli, item.endTimeMilli, res)
        holder.quality.text = convertNumericQualityToString(item.sleepQuality, res)
        holder.qualityImage.setImageResource(
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


//        if (itemView.sleepQuality <= 1) {
//            holder.textView.setTextColor(Color.RED)
//        } else {
//            // reset
//            holder.textView.setTextColor(Color.BLACK)
//        }
//        holder.textView.text = itemView.sleepQuality.toString()

    }


}



