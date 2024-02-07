package com.borja.android.basicnotifications.data

import com.google.firebase.messaging.FirebaseMessagingService
import javax.inject.Inject

class MessagingService @Inject constructor() : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    fun sendRegistrationToken(token: String){
        //llamada al backend
    }

}