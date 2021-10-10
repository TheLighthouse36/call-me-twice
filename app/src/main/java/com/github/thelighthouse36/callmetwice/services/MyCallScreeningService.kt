package com.github.thelighthouse36.callmetwice.services

import android.os.Build
import android.telecom.Call
import android.telecom.CallScreeningService
import androidx.annotation.RequiresApi
import com.github.thelighthouse36.callmetwice.addBubble
import com.github.thelighthouse36.callmetwice.bubbleEnvContainsNumber
import com.github.thelighthouse36.callmetwice.commons.events.MessageEvent
import com.github.thelighthouse36.callmetwice.commons.parseCountryCode
import com.github.thelighthouse36.callmetwice.commons.removeTelPrefix
import com.github.thelighthouse36.callmetwice.commons.utils.NotificationManagerImpl
import org.greenrobot.eventbus.EventBus

@RequiresApi(Build.VERSION_CODES.N)
class MyCallScreeningService : CallScreeningService() {

    private val notificationManager = NotificationManagerImpl()

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onScreenCall(callDetails: Call.Details) {
        val phoneNumber = getPhoneNumber(callDetails)
        var response = CallResponse.Builder()
        response = handlePhoneCall(response, phoneNumber)

        respondToCall(callDetails, response.build())
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun handlePhoneCall(
        response: CallResponse.Builder,
        phoneNumber: String
    ): CallResponse.Builder {
        if (!bubbleEnvContainsNumber(phoneNumber)) {
            response.apply {
                setSilenceCall(true)
                setSkipCallLog(false)
                addBubble(phoneNumber, 10);
                //addString(phoneNumber)
                displayToast(String.format("Silenced call from %s", phoneNumber))
            }
        } else {
            displayToast(String.format("Incoming call from %s", phoneNumber))
        }
        return response
    }

    private fun getPhoneNumber(callDetails: Call.Details): String {
        return callDetails.handle.toString().removeTelPrefix().parseCountryCode()
    }

    private fun displayToast(message: String) {
        notificationManager.showToastNotification(applicationContext, message)
        EventBus.getDefault().post(MessageEvent(message))
    }

}