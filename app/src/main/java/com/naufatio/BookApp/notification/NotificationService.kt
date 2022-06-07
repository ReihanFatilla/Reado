package com.naufatio.BookApp.notification

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.naufatio.BookApp.R
import java.util.*
import java.util.concurrent.ThreadLocalRandom

class NotificationService : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val notification = NotificationCompat.Builder(context, channelID)
            .setSmallIcon(R.drawable.ic_book)
            .setContentTitle(intent.getStringExtra(titleExtra))
            .setContentText(intent.getStringExtra(messageExtra))
            .build()

        val manager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(notificationID, notification)
    }

    fun scheduleNotification(context: Context, title: String, message: String) {
        val intent = Intent(context, NotificationService::class.java)
        intent.putExtra(titleExtra, title)
        intent.putExtra(messageExtra, message)

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val time = getRandomTime(getBeginTime(), getEndTime())
        Log.i("Notification", "scheduleNotification: ${time.time}")

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            notificationID,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.set(
                AlarmManager.RTC_WAKEUP,
                time.timeInMillis,
                pendingIntent
            )
        }
    }

    private fun getRandomTime(begin: Calendar, end: Calendar): Calendar {
        val randomNum: Long =
            ThreadLocalRandom.current().nextLong(begin.timeInMillis, end.timeInMillis + 1)
        val res = Calendar.getInstance()
        res.timeInMillis = randomNum
        return res
    }

    private fun getBeginTime(): Calendar {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 11)
        calendar.set(Calendar.MINUTE, 1)
        calendar.set(Calendar.SECOND, 0)
        return calendar
    }

    private fun getEndTime(): Calendar {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 11)
        calendar.set(Calendar.MINUTE, 5)
        calendar.set(Calendar.SECOND, 30)
        return calendar
    }

//    fun createNotificationChannel(context: Context) {
//        val name = "Notif Channel"
//        val desc = "A Description of the Channel"
//        val importance = NotificationManager.IMPORTANCE_DEFAULT
//        val channel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel(channelID, name, importance)
//        } else {
//            TODO("VERSION.SDK_INT < O")
//        }
//        channel.description = desc
//        val notificationManager =
//            context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
//        notificationManager.createNotificationChannel(channel)
//    }
//    private fun showAlert(context: Context, title: String, message: String) {
//        AlertDialog.Builder(context)
//            .setTitle("Reado Book Recommendation")
//            .setMessage(
//                "Title: " + title + "\nMessage: " + message
//            )
//            .setPositiveButton("Okay") { _, _ -> }
//            .show()
//    }

    companion object {
        const val notificationID = 1
        const val channelID = "channel1"
        const val titleExtra = "titleExtra"
        const val messageExtra = "messageExtra"
    }
}
