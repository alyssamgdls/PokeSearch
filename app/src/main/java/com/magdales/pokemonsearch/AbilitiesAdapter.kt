package com.magdales.pokemonsearch

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created by Lai on 3/16/2018.
 */
class AbilitiesAdapter(val abilitieslist: ArrayList<Ability>) : RecyclerView.Adapter<AbilitiesAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.abilityText.text = abilitieslist[position].abilities
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return abilitieslist.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val abilityText = itemView.findViewById(R.id.textView1) as TextView
    }
}