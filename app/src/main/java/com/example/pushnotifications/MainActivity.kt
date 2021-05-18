package com.example.pushnotifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*
        Notifications are handled by system not by our app
         */
        val nm = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        //channels work for android Oreo and above
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            nm.createNotificationChannel(NotificationChannel("first","default",NotificationManager.IMPORTANCE_DEFAULT))
        }

        //A simple notification
        button.setOnClickListener {
            val simpleNotification = NotificationCompat.Builder(this,"first").setContentTitle("Simple Title")
                    .setContentText("This is sample description of notification!")
                    .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .build()

            nm.notify(1,simpleNotification)
        }

        //Clickable Notifications
        button2.setOnClickListener {
            val i = Intent()
            i.action = Intent.ACTION_VIEW
            i.data = Uri.parse("https://www.google.com")

            val pi = PendingIntent.getActivity(this,123,i,PendingIntent.FLAG_UPDATE_CURRENT)

            val clickableNotification = NotificationCompat.Builder(this,"fist")
                    .setContentTitle("Simple Title")
                    .setContentIntent(pi)
                    .setAutoCancel(true) //when we click on a notification it automatically dismiss the notification from notification pannel
                    .setContentText("This is sample description of notification!")
                    .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .build()

            nm.notify(2,clickableNotification)
        }

        //Notification with a button
        button3.setOnClickListener {
            val i = Intent()
            i.action = Intent.ACTION_VIEW
            i.data = Uri.parse("https://www.google.com")

            val pi = PendingIntent.getActivity(this,123,i,PendingIntent.FLAG_UPDATE_CURRENT)

            val clickableNotification = NotificationCompat.Builder(this,"fist")
                    .setContentTitle("Simple Title")
                    .addAction(R.drawable.ic_launcher_foreground,"CLICK ME",pi)
                    .setAutoCancel(true) //when we click on a notification it automatically dismiss the notification from notification pannel
                    .setContentText("This is sample description of notification!")
                    .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .build()

            nm.notify(3,clickableNotification)
        }
    }
}