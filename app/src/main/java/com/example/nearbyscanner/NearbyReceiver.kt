package com.example.nearbyscanner

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.google.android.gms.nearby.Nearby
import com.google.android.gms.nearby.messages.Message
import com.google.android.gms.nearby.messages.MessageListener
import timber.log.Timber

class NearbyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Timber.d("onReceive()")
        Nearby.getMessagesClient(context).handleIntent(intent, object : MessageListener() {
            override fun onFound(message: Message) {
                Timber.d("Found $message")
            }

            override fun onLost(message: Message) {
                Timber.d("Lost $message")
            }
        })
    }
}