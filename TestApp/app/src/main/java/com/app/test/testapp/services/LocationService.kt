package com.app.test.testapp.services

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.IBinder
import android.support.v4.app.NotificationCompat
import android.widget.Toast
import com.app.test.testapp.view.activities.MainActivity
import com.urbamap.urbamap.utils.SharedPreferenceKeys
import com.urbamap.urbamap.utils.SharedPreferenceManager


/**
 * Created by Enrique on 2/10/2018.
 */
class LocationService : Service() {

    companion object {
        private var onLocationChange: ((location: Location) -> Unit)? = null
        fun setOnLocationListener(onLocationChangeFun: ((location: Location) -> Unit)?) {
            onLocationChange = onLocationChangeFun
        }
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        val restartService = Intent(applicationContext, this.javaClass)
        restartService.`package` = packageName;
        val restartServicePI = PendingIntent.getService(
                applicationContext, 1, restartService,
                PendingIntent.FLAG_ONE_SHOT)

    }


    private val LOCATION_INTERVAL = 5000
    private val LOCATION_DISTANCE = 10f

    private val locationManager: LocationManager by lazy {
        getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

    private val customLocationListenerGPS by lazy {
        CustomLocationListener(LocationManager.GPS_PROVIDER)
    }

    private val locationListeners by lazy {
        arrayOf(customLocationListenerGPS)
    }

    protected fun isGPSProviderEnabled(): Boolean {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    fun initLocationGPS() {
        checkLocation()
        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_INTERVAL.toLong(), LOCATION_DISTANCE, locationListeners[0])
        } catch (secEx: SecurityException) {
            Toast.makeText(this@LocationService, "Security exception", Toast.LENGTH_SHORT).show()
        } catch (ileEx: IllegalArgumentException) {
            Toast.makeText(this@LocationService, "Illegal exception", Toast.LENGTH_SHORT).show()
        }
    }

    fun checkLocation(): Boolean {
        val enable = isLocationEnabled()
        return enable
    }

    fun isLocationEnabled(): Boolean {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }


    override fun onCreate() {
        initLocationGPS()

    }

    override fun onDestroy() {
        super.onDestroy()
        locationManager.removeUpdates(customLocationListenerGPS)
    }


    private inner class CustomLocationListener(provider: String) : LocationListener {

        override fun onLocationChanged(p0: Location?) {
            p0?.let { newLocation ->
                Toast.makeText(this@LocationService, p0.toString(), Toast.LENGTH_SHORT).show()
                val genre = SharedPreferenceManager[SharedPreferenceKeys.GENERATE_NOTIFI, false]
                val forceGenre = SharedPreferenceManager[SharedPreferenceKeys.GENERATE_NOTIFI, false]

                if (forceGenre) {
                    sendNotification()
                }
                onLocationChange?.invoke(p0)

                SharedPreferenceManager[SharedPreferenceKeys.LAST_KNOW_LAT_LOCATION] = newLocation.latitude.toFloat()
                SharedPreferenceManager[SharedPreferenceKeys.LAST_KNOW_LON_LOCATION] = newLocation.longitude.toFloat()
            }
        }

        override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
//            Toast.makeText(this@LocationActivity, "onStatusChanged: $p0", Toast.LENGTH_SHORT).show()
        }

        override fun onProviderEnabled(p0: String?) {
//            Toast.makeText(this@LocationActivity, "onProviderEnabled: $p0", Toast.LENGTH_SHORT).show()
        }

        override fun onProviderDisabled(p0: String?) {
//            Toast.makeText(this@LocationActivity, "onProviderDisabled: $p0", Toast.LENGTH_SHORT).show()
        }
    }

    fun sendNotification() {
        val intent = Intent(this@LocationService, MainActivity::class.java)
        val contentIntent = PendingIntent.getActivity(this@LocationService, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)


        val mBuilder = NotificationCompat.Builder(this)
                .setSmallIcon(android.R.drawable.ic_lock_power_off)
                .setContentTitle("New Location")
                .setContentText("Show new location info...")
                .setContentIntent(contentIntent)

        val mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mNotificationManager.notify(1, mBuilder.build())
    }

}