# nearby-scanner
PoC for BLE scanning via Google Nearby Play Service

To verify that Nearby scanning does not fire the BroadcastReceiver on Android 12:

* Grant background location permission to the app (via the app settings)
* Grant nearby scan permission to the app (via the app settings)
* Start the app
* Press 'SUBSCRIBE' button
* Subscription to Nearby messages is started successfully but `NearbyReceiver` does not fire although beacon in range
