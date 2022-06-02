package com.naufatio.BookApp.notification
//
//import android.app.AlarmManager
//import android.app.AlertDialog
//import android.app.PendingIntent
//import android.content.BroadcastReceiver
//import android.content.Context
//import android.content.Context.ALARM_SERVICE
//import android.content.DialogInterface
//import android.content.Intent
//import android.os.PowerManager
//import androidx.core.content.ContextCompat.getSystemService
//import java.util.*
//
//
//class AlarmService(): BroadcastReceiver() {
//    override fun onReceive(context: Context, intent: Intent?) {
//        val pm = context.getSystemService(Context.POWER_SERVICE) as PowerManager
//        val wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK, "NotificationService")
//        wakeLock.acquire()
//        val service1 = Intent(context, AlarmService::class.java)
//        context.startService(service1)
//    }
//
//    fun setAlarm(context: Context) {
//        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager?
//        val alarmIntent = Intent(context, AlarmService::class.java)
//        val pendingIntent = PendingIntent.getBroadcast(
//            context,
//            1,
//            alarmIntent,
//            PendingIntent.FLAG_ONE_SHOT
//        )
//        val alarmStartTime: Calendar = Calendar.getInstance()
//        alarmStartTime.set(Calendar.HOUR_OF_DAY, hour)
//        alarmStartTime.set(Calendar.MINUTE, minute)
//        alarmManager!![AlarmManager.RTC_WAKEUP, alarmStartTime.getTimeInMillis()] = pendingIntent
//        val builder: AlertDialog.Builder = Builder(this)
//        builder.setTitle("Message Sent!").setCancelable(false).setNegativeButton("Close",
//            DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })
//        val alert: AlertDialog = builder.create()
//        alert.setTitle("TITLE")
//        alert.setMessage("Notification was set.")
//        alert.show()
//    }
//
////    fun showRandomNotification(context: Context, bookTitle: String) {
////        val builder: NotificationCompat.Builder = NotificationCompat.Builder(context)
////            .setSmallIcon(R.drawable.ic_book)
////            .setContentTitle("Read a book now!")
////            .setContentText("A book called \"$bookTitle\" is now available!")
////            .setAutoCancel(true)
////            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
////
////        val notificationIntent = Intent(context, AlarmReceiver::class.java)
////        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
////        notificationIntent.putExtra("message", "A book called \"$bookTitle\" is now available!")
////
////        val pendingIntent = PendingIntent.getActivity(
////            context, 0, notificationIntent,
////            PendingIntent.FLAG_UPDATE_CURRENT
////        )
////
////        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
////            val channel = NotificationChannel(NotificationManager.IMPORTANCE_DEFAULT)
////            channel.enableVibration(true)
////            channel.vibrationPattern = longArrayOf(1000, 1000, 1000, 1000)
////            builder.setChannelId(channelId)
////            notificationManager.createNotificationChannel(channel)
////        }
////
////        val notification = builder.build()
////        notificationManager.notify(notificationId, notification)
////    }
//}