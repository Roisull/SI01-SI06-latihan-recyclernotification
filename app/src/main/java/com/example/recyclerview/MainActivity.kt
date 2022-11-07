package com.example.recyclerview

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.adapter.AdapterRecyclerView
import com.example.recyclerview.data.DataRecyclerView

class MainActivity : AppCompatActivity() {
    private val channelId = "Contoh Channel ID"
    private val notificationId = 101
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listData = arrayListOf(
            DataRecyclerView(R.drawable.logo, "recycler item 1", "Desc Recycler item 1"),
            DataRecyclerView(R.drawable.logo, "recycler item 2", "Desc Recycler item 2"),
            DataRecyclerView(R.drawable.logo, "recycler item 3", "Desc Recycler item 3"),
            DataRecyclerView(R.drawable.logo, "recycler item 4", "Desc Recycler item 4"),
            DataRecyclerView(R.drawable.logo, "recycler item 5", "Desc Recycler item 5"),
            DataRecyclerView(R.drawable.logo, "recycler item 6", "Desc Recycler item 6"),
            DataRecyclerView(R.drawable.logo, "recycler item 7", "Desc Recycler item 7")
        )

//        make object for adapter
        val adapter = AdapterRecyclerView(listData)

//        make layout manager
        val layoutManajer = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

//        call recyclerview
        val recyclerView = findViewById<RecyclerView>(R.id.rvRecyclerView)

//        set layput manajer into recyclerview
        recyclerView.layoutManager = layoutManajer

//        set adapter to recycler view
        recyclerView.adapter = adapter
    }
    private fun membuatNotifikasi() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val nama = "Judul Notifikasi"
            val deskripsi = "Deskripsi Notifikasi"
            val importance:Int =
                NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId,
                nama, importance).apply {
                description = deskripsi
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as
                        NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
    private fun kirimNotifikasi(){
        val intent = Intent(this,
            MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                    Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent =
            PendingIntent.getActivity(this,0, intent,0)
        val bitmap =
            BitmapFactory.decodeResource(applicationContext.resources,
                R.drawable.logo)
        val bitmapLargeIcon =
            BitmapFactory.decodeResource(applicationContext.resources,
                R.drawable.logo)
        val builder = NotificationCompat.Builder(this,
            channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Notifikasi")
            .setContentText("Ini adalah notifikasi sebagai pesan informasi")
                .setLargeIcon(bitmapLargeIcon)
                .setStyle(NotificationCompat.BigTextStyle().bigText(""))
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        with(NotificationManagerCompat.from(this)){
                            notify(notificationId, builder.build())
                        }
    }

}