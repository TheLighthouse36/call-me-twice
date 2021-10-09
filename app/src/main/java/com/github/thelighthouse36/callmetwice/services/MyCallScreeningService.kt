package com.github.thelighthouse36.callmetwice.services

import android.os.Build
import android.telecom.Call
import android.telecom.CallScreeningService
import androidx.annotation.RequiresApi
import com.github.thelighthouse36.callmetwice.commons.FORBIDDEN_PHONE_CALL_NUMBER
import com.github.thelighthouse36.callmetwice.commons.events.MessageEvent
import com.github.thelighthouse36.callmetwice.commons.parseCountryCode
import com.github.thelighthouse36.callmetwice.commons.removeTelPrefix
import com.github.thelighthouse36.callmetwice.commons.utils.NotificationManagerImpl
import org.greenrobot.eventbus.EventBus

@RequiresApi(Build.VERSION_CODES.N)
class MyCallScreeningService : CallScreeningService() {

    private val notificationManager = NotificationManagerImpl()

    override fun onScreenCall(callDetails: Call.Details) {
        val phoneNumber = getPhoneNumber(callDetails)
        var response = CallResponse.Builder()
        response = handlePhoneCall(response, phoneNumber)

        respondToCall(callDetails, response.build())
    }

    private fun handlePhoneCall(
        response: CallResponse.Builder,
        phoneNumber: String
    ): CallResponse.Builder {
        if (phoneNumber == FORBIDDEN_PHONE_CALL_NUMBER) {
            response.apply {
                setRejectCall(true)
                setDisallowCall(true)
                setSkipCallLog(false)
                //
                displayToast(String.format("Rejected call from %s", phoneNumber))
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