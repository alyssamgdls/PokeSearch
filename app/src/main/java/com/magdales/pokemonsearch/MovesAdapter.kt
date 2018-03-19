package com.magdales.pokemonsearch

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created by Lai on 3/16/2018.
 */
class MovesAdapter(val moveslist: ArrayList<Moves>) : RecyclerView.Adapter<MovesAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: MovesAdapter.ViewHolder, position: Int) {
        holder.moveText.text = moveslist[position].move
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovesAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MovesAdapter.ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return moveslist.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val moveText = itemView.findViewById(R.id.textView1) as TextView
    }
}