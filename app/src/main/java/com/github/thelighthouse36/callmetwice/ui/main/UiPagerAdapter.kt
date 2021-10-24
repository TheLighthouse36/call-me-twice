package com.github.thelighthouse36.callmetwice.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class UiPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    var phoneLogFragment = PhoneLogFragment()
    var bubbleFragment = BubbleFragment()
    var settingsFragment = SettingsFragment()



    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                this.phoneLogFragment
            }
            1 -> {
                this.bubbleFragment
            }
            2 -> {
                this.settingsFragment
            }
            else -> {
                return PlaceholderFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Phone Log"
            1 -> "Bubble Enviroment"
            2 -> "Preferences"
            else -> {
                return "ERROR"
            }
        }
    }

}