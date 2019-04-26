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
import com.yelp.fusion.client.models.Business


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

        val rand = List(10){ Random.nextInt(0,100)}

        executeCommand()

        button.setOnClickListener {
            pullRestaraunt()
        }


    }

    private fun pullRestaraunt() {

        var apiKey : String =  "jxjG2G9MV20rNsovJ77zOtnsu665qcvWfDXHMgBWWIKdZuwhk-U2UJ0fJLnCy0Css5QrzJWAllz3l1mJgTxXPmAS2QtKvurBj1AALzoo5dKsDg63iFGZ0G8EdRHDXHYx"

        val apiFactory = YelpFusionApiFactory()
        val yelpFusionApi = apiFactory.createAPI(apiKey)
        val params = HashMap<String, String>()

        params.put("term", "indian food")
        params.put("location", "Cincinnati")
        params.put("limit", "20")

        val call = yelpFusionApi.getBusinessSearch(params)
        val response = call.execute()

       var business: ArrayList<Business> = response.body().businesses

    }

    private fun executeCommand(): String {
        var string : String = "no text"

        var apiKey : String =  "jxjG2G9MV20rNsovJ77zOtnsu665qcvWfDXHMgBWWIKdZuwhk-U2UJ0fJLnCy0Css5QrzJWAllz3l1mJgTxXPmAS2QtKvurBj1AALzoo5dKsDg63iFGZ0G8EdRHDXHYx"

        val apiFactory = YelpFusionApiFactory()
        val yelpFusionApi = apiFactory.createAPI(apiKey)


        return string
    }


}
