package com.magdales.pokemonsearch

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created by Lai on 3/16/2018.
 */
class StatsAdapter(val statslist: ArrayList<Stats>) : RecyclerView.Adapter<StatsAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: StatsAdapter.ViewHolder, position: Int) {
        holder.baseStatText.text = statslist[position].base_stat
        holder.statText.text = statslist[position].stats
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatsAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return StatsAdapter.ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return statslist.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val baseStatText = itemView.findViewById(R.id.textView1) as TextView
        val statText = itemView.findViewById(R.id.textView2) as TextView
    }
}