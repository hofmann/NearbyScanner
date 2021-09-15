# nearby-scanner
PoC for BLE scanning via Google Nearby Play Service

To verify that Nearby scanning does not fire the BroadcastReceiver on Android 12:

* Grant background location permission to the app (via the app settings)
* Grant nearby scan permission to the app (via the app settings)
* Simulate a beacon with UUID 11111111-2222-3333-4444-555555555555 and any major/minor value e.g. via https://play.google.com/store/apps/details?id=net.alea.beaconsimulator&hl=de&gl=US
* Start the app
* Press 'SUBSCRIBE' button
* Subscription to Nearby messages is started successfully but `NearbyReceiver` does not fire although beacon in range
