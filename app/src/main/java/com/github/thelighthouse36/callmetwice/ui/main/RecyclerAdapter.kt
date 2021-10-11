package com.github.thelighthouse36.callmetwice.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.thelighthouse36.callmetwice.R
import com.github.thelighthouse36.callmetwice.bubbleEnvironment

class RecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_bubble, parent, false)
        return ViewHolder(v)
    }

    /**
     * not like the tutorial
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        myOnBindViewHolder(holder as ViewHolder, position)
    }

    private fun myOnBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemNumber.text = bubbleEnvironment[position].number
        holder.itemLife.text = bubbleEnvironment[position].life.toString()
//        holder.itemNumber.text = sampleNumbers[position]
//        holder.itemLife.text = sampleLives[position]

    }


    override fun getItemCount() = bubbleEnvironment.size


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemNumber: TextView
        var itemLife: TextView

        init {
            itemNumber = itemView.findViewById(R.id.number_bubble)
            itemLife = itemView.findViewById(R.id.life_bubble)

//            itemView.setOnClickListener {
//                var position: Int = adapterPosition
//                val context = itemView.context
//                val intent =
//            }

        }



    }




}