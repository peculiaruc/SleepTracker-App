package com.pecpaker.sleeptrackerapp.ui.sleepTraker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pecpaker.sleeptrackerapp.R
import com.pecpaker.sleeptrackerapp.dataSource.local.SleepNight
import kotlinx.android.synthetic.main.fragment_sleep_quality.view.*

class SleepTrackerAdapter : RecyclerView.Adapter<SleepTrackerAdapter.ViewHolder>() {

    var data = listOf<SleepNight>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val textView = itemView.findViewById<TextView>(R.id.title_text)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.text_item_view, parent, false) as TextView
        return ViewHolder(itemView)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemView = data[position]
        holder.textView.text = itemView.sleepQuality.toString()

    }


}



