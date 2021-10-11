package com.github.thelighthouse36.callmetwice.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.thelighthouse36.callmetwice.Bubble
import com.github.thelighthouse36.callmetwice.R
import com.github.thelighthouse36.callmetwice.bubbleEnvironment
import kotlinx.android.synthetic.main.bubble_recycler.*

class BubbleFragment : Fragment() {

    //private var layoutManager: RecyclerView.LayoutManager? = null
    private lateinit var adapter: RecyclerAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        this.adapter = RecyclerAdapter()
        return inflater.inflate(R.layout.bubble_recycler, container, false)
    }


    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        recycler_view.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView
            adapter = RecyclerAdapter()
        }
    }

    fun refresh() {
        recycler_view.adapter?.notifyDataSetChanged()
    }

    fun remove(b : Bubble) {
        val i = bubbleEnvironment.indexOf(b)
        bubbleEnvironment.removeAt(i)
        recycler_view.adapter?.notifyDataSetChanged()
    }






}