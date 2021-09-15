package com.example.nearbyscanner

import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.nearby.Nearby
import com.google.android.gms.nearby.messages.*
import timber.log.Timber
import java.util.*

class MainActivity : AppCompatActivity() {

    private val nearbyClient: MessagesClient
        get() {
            val options = MessagesOptions.Builder()
                .setPermissions(NearbyPermissions.BLE)
                .build()

            return Nearby.getMessagesClient(applicationContext, options)
        }

    private val pendingIntent: PendingIntent
        get() = PendingIntent.getBroadcast(
            applicationContext,
            94,
            Intent(applicationContext, NearbyReceiver::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun subscribeNearby(view: View) {
        Timber.d("Subscribing...")
        val messageFilterBuilder = MessageFilter.Builder()
        messageFilterBuilder.includeIBeaconIds(
            UUID.fromString("11111111-2222-3333-4444-555555555555"),
            null,
            null
        )
        val subscriptionOptions = SubscribeOptions.Builder()
            .setStrategy(Strategy.BLE_ONLY)
            .setFilter(messageFilterBuilder.build())
            .build()

        nearbyClient.subscribe(pendingIntent, subscriptionOptions)
            .addOnSuccessListener {
                Timber.d("Subscription successful")
            }
            .addOnFailureListener {
                Timber.d("Subscription failed: ${it.message}")
            }
            .addOnCanceledListener {
                Timber.d("Subscription canceled")
            }
    }

    fun unsubscribeNearby(view: View) {
        Timber.d("Unsubscribing...")

        nearbyClient.unsubscribe(pendingIntent)
            .addOnSuccessListener {
                Timber.d("Unsubscribe successful")
            }
            .addOnFailureListener {
                Timber.d("Unsubscribe failed: ${it.message}")
            }
            .addOnCanceledListener {
                Timber.d("Unsubscribe canceled")
            }
    }
}