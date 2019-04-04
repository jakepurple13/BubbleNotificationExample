package com.programmersbox.bubblenotificationexample

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Icon
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val channel = NotificationChannel("bubble", "bubble", NotificationManager.IMPORTANCE_DEFAULT)
        val mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mNotificationManager.createNotificationChannel(channel)
        button.setOnClickListener {
            GlobalScope.launch {
                delay(5000)
                //launch notification
                // Create bubble intent
                val target = Intent(this@MainActivity, BubbleActivity::class.java)
                val bubbleIntent = PendingIntent.getActivity(this@MainActivity, 0, target, 0 /* flags */)

                // Create bubble metadata
                val bubbleData = Notification.BubbleMetadata.Builder()
                    .setDesiredHeight(600)
                    // Note: although you can set the icon is not displayed in Q Beta 2
                    .setIcon(Icon.createWithResource(this@MainActivity, R.mipmap.ic_launcher))
                    .setIntent(bubbleIntent)
                    .build()

                // Create notification
                val chatBot = Person.Builder()
                    .setBot(true)
                    .setName("BubbleBot")
                    .setImportant(true)
                    .build()

                val builder = Notification.Builder(this@MainActivity, "bubble")
                    .setContentIntent(null)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setBubbleMetadata(bubbleData)

                mNotificationManager.notify(3, builder.build())

            }
        }
    }
}
