package com.example.internetspeedchecker.prersentation.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.internetspeedchecker.R
import com.example.internetspeedchecker.data.local.entity.SpeedEntity
import com.example.internetspeedchecker.data.repository.SpeedRepositoryImpl
import com.example.internetspeedchecker.prersentation.activity.MainActivity
import com.example.internetspeedchecker.util.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Timer
import javax.inject.Inject
import kotlin.concurrent.schedule

@AndroidEntryPoint
class SpeedService : Service() {
    @Inject
    lateinit var repository: SpeedRepositoryImpl


    override fun onCreate() {
        super.onCreate()
        initData()
    }

    private fun initData() {
        val cm = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        // Network Capabilities of Active Network
        val nc = cm.getNetworkCapabilities(cm.activeNetwork)

        val timer = Timer()
        timer.schedule(0, 10000) {
            // DownSpeed in MBPS
            val downSpeed  = (nc?.linkDownstreamBandwidthKbps)?.div(1000)

            // UpSpeed  in MBPS
            val upSpeed = (nc?.linkUpstreamBandwidthKbps)?.div(1000)

            if (downSpeed != null && upSpeed!=null) {
                prepareForegroundNotification(downSpeed,upSpeed)
                CoroutineScope(Dispatchers.Main).launch {
                    val data = SpeedEntity(0,upSpeed,downSpeed)
                    repository.insertSpeed(data)
                }
            }
        }



    }



    private fun prepareForegroundNotification(upLinkSpeed:Int,downLink:Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                Constants.CHANNEL_ID,
                "Speed Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(
                NotificationManager::class.java
            )
            manager.createNotificationChannel(serviceChannel)
        }
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            Constants.SERVICE_SPEED_REQUEST_CODE,
            notificationIntent, PendingIntent.FLAG_IMMUTABLE
        )
        val notification: Notification = NotificationCompat.Builder(this, Constants.CHANNEL_ID)
            .setContentTitle(getString(R.string.app_name))
            .setContentTitle("Uplink Speed - $upLinkSpeed Mbps\nDownLink Speed - $downLink Mbps")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentIntent(pendingIntent)
            .build()
        startForeground(Constants.SPEED_SERVICE_NOTIF_ID, notification)
    }


    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

}