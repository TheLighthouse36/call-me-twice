package com.github.thelighthouse36.callmetwice.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.github.thelighthouse36.callmetwice.R
import com.github.thelighthouse36.callmetwice.services.MyCallScreeningService.Companion.time
import com.github.thelighthouse36.callmetwice.toTime
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : Fragment() {


    lateinit var timeButton: Button
    lateinit var resetButton: Button
    lateinit var errorView: TextView

    //set up a button with an on click listener so that I can do some error messaging and stuff
    //for the contacts just make a switch for it/toggle thing the link for the second option is below
    // https://stackoverflow.com/questions/26859338/check-incoming-number-is-stored-in-contacts-list-or-not-android/26859482
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val currView : View = inflater!!.inflate(R.layout.fragment_settings, container, false)
        timeButton = currView.findViewById(R.id.bubble_lifetime_button) as Button
        resetButton = currView.findViewById(R.id.reset_button) as Button
        errorView = currView.findViewById(R.id.errorView)




        return currView

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        timeButton?.setOnClickListener(View.OnClickListener { view ->
            //errorView.text = "TIME BAYBE"
            time = toTime(bubble_lifetime.text.toString())
        })
        resetButton?.setOnClickListener(View.OnClickListener { view ->
            errorView.text = "Reset BAYBAYDB"
            //bubbleEnvironment = arrayListOf()
        })
    }


}