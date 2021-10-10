package com.github.thelighthouse36.callmetwice.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.github.thelighthouse36.callmetwice.Bubble
import com.github.thelighthouse36.callmetwice.bubbleEnvironment


class BubbleFragment : Fragment() {

    lateinit var adapter: ArrayAdapter<Bubble>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(com.github.thelighthouse36.callmetwice.R.layout.fragment_bubble, container, false)
        val listView = view.findViewById<ListView>(com.github.thelighthouse36.callmetwice.R.id.bubble_list)
        adapter =
            activity?.let { ArrayAdapter<Bubble>(it, android.R.layout.simple_list_item_1, bubbleEnvironment) }!!
        adapter.setNotifyOnChange(true)
        listView.adapter = adapter

        return view

    }

    companion object {
        fun notify(bubbleFragment: BubbleFragment) {
            bubbleFragment.adapter.notifyDataSetChanged()
        }
        fun remove(bubbleFragment: BubbleFragment, bubble: Bubble) {
            //val pos = bubbleFragment.adapter.
            bubbleFragment.adapter.remove(bubble)

        }
    }
}



