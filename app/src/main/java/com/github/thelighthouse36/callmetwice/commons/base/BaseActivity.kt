package com.github.thelighthouse36.callmetwice.commons.base

import androidx.appcompat.app.AppCompatActivity
import com.github.thelighthouse36.callmetwice.commons.events.SingleLiveEvent
import com.github.thelighthouse36.callmetwice.commons.events.UiEvent

abstract class BaseActivity : AppCompatActivity() {

    /**
     * Event that can be received in every activity that extends [BaseActivity]
     */
    val uiEvent = SingleLiveEvent<UiEvent>()

}