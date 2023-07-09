package com.kj.fcmpushnotification

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

const val channelId="notification_channel"
const val channelName="com.kj.fcmpushnotification"

class MyFirebaseMessagingService:FirebaseMessagingService() {
    //Generate the notification

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if(remoteMessage.getNotification()!=null){

            generateNotification(remoteMessage.notification!!.title!!,remoteMessage.notification!!.body!!)

        }
    }

    @SuppressLint("RemoteViewLayout")
    fun getRemoteView(title: String,message: String):RemoteViews{

        val remoteView=RemoteViews("com.kj.fcmpushnotification",R.layout.notification)
        remoteView.setTextViewText(R.id.title,title)
        remoteView.setTextViewText(R.id.title,message)
        remoteView.setImageViewResource(R.id.app_logo,R.drawable.kj)

        return remoteView
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun generateNotification(title:String, message:String){

        val intent=Intent(this,MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT)

        //Channel id,Channel name
        var builder:NotificationCompat.Builder=NotificationCompat.Builder(
            applicationContext,
            channelId
        )
            .setSmallIcon(R.drawable.kj)
            .setAutoCancel(true)
            .setVibrate(longArrayOf(1000,1000,1000,1000))
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingIntent)

        builder= builder.setContent(getRemoteView(title,message))

        val notificationManager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            val notificationChannel=NotificationChannel(channelId, channelName,NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)
        }

        notificationManager.notify(0,builder.build())
    }
    //attach the notification created with the custom layout

    //show the notification
}