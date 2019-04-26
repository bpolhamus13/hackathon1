package com.example.hackathon

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import com.yelp.fusion.client.connection.YelpFusionApiFactory
import com.yelp.fusion.client.models.Business

import kotlinx.android.synthetic.main.activity_tellmemore.*
import kotlin.random.Random

class tellmemore : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tellmemore)


        var apiKey: String =
            "jxjG2G9MV20rNsovJ77zOtnsu665qcvWfDXHMgBWWIKdZuwhk-U2UJ0fJLnCy0Css5QrzJWAllz3l1mJgTxXPmAS2QtKvurBj1AALzoo5dKsDg63iFGZ0G8EdRHDXHYx"

        val apiFactory = YelpFusionApiFactory()
        val yelpFusionApi = apiFactory.createAPI(apiKey)
        val params = HashMap<String, String>()


val choose : String = intent.getStringExtra("Term")
        if (intent.getStringExtra("Term") != null || !choose.equals("None")) {
            params.put("term", intent.getStringExtra("Term"))
        }
        params.put("location", "Cincinnati")
        if (intent.getStringExtra("closed").toString() == "Open Now"){
            params.put("open_now", "1")
        }
        when (intent.getStringExtra("rating")){
            "$" -> params.put("price", "1")
            "$$" -> params.put("price", "1,2")
            "$$$" -> params.put("price", "1,2,3")
            "$$$$" -> params.put("price", "1,2,3,4")
        }
        params.put("limit", "20")

        val call = yelpFusionApi.getBusinessSearch(params)
        val response = call.execute()

        var business: ArrayList<Business> = response.body().businesses

        val random : Int = Random.nextInt(0,business.size)
        textView15.text = business[random].name
        address.text = business[random].location.address1 + "  "+business[random].location.city + "  "+business[random].location.state +  "  "+business[random].location.zipCode
        Price.text = business[random].price
        phone.text = business[random].phone
        rating.text = business[random].rating.toString()


    }

}
