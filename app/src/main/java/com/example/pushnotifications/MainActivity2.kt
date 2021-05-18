package com.example.pushnotifications

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val nm = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        //channels work for android Oreo and above
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel("first" ,"default",NotificationManager.IMPORTANCE_HIGH)
            channel.apply {
                enableLights(true)
                enableVibration(true)
            }
            nm.createNotificationChannel(channel)
        }

        //Heads-up notification
        button4.setOnClickListener {
            val builder =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    Notification.Builder(this,"first")
                }
                else{
                    Notification.Builder(this)
                        .setPriority(Notification.PRIORITY_MAX)
                        .setDefaults(Notification.DEFAULT_VIBRATE or Notification.DEFAULT_LIGHTS)
                }
            val simpleNotification = builder
                .setContentText("This is sample description of notification!")
                .setContentTitle("Simple Title")
                .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                .build()

            nm.notify(1,simpleNotification)
        }
    }
}