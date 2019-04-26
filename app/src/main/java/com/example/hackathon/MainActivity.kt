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
import android.content.Intent
import com.yelp.fusion.client.connection.YelpFusionApi
import com.yelp.fusion.client.models.SearchResponse
import okhttp3.Response
import android.os.AsyncTask.execute
import android.os.StrictMode
import com.yelp.fusion.client.models.Business


class MainActivity : AppCompatActivity() {

    private lateinit var term : String
    private lateinit var rating : String
    private lateinit var closed : String
    private lateinit var textMessage: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (android.os.Build.VERSION.SDK_INT > 9) {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
    }

        button.setOnClickListener {
            term = spinner.selectedItem.toString()
            rating = spinner2.selectedItem.toString()
            closed = spinner4.selectedItem.toString()



            val tellMeMore = Intent(this, tellmemore::class.java)
            tellMeMore.putExtra("Term", term)
            tellMeMore.putExtra("rating", rating)
            tellMeMore.putExtra("closed", closed)
            startActivity(tellMeMore)


        }


    }

    private fun pullRestaraunt() :ArrayList<Business> {

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
        return business
    }


}
