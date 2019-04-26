package com.example.hackathon

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import com.yelp.fusion.client.connection.YelpFusionApiFactory
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import kotlin.random.Random
import android.R.attr.apiKey
import com.yelp.fusion.client.connection.YelpFusionApi
import com.yelp.fusion.client.models.SearchResponse
import okhttp3.Response
import android.os.AsyncTask.execute
import android.os.StrictMode






class MainActivity : AppCompatActivity() {

    private lateinit var textMessage: TextView

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                textMessage.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                textMessage.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                textMessage.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (android.os.Build.VERSION.SDK_INT > 9) {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        val yelpCallback = YelpCallback()


        button.setOnClickListener {
            textView2.setText(yelpCallback.pullRestaraunt())


        }
    }


}
