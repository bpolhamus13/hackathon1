package com.example.hackathon

import com.yelp.fusion.client.connection.YelpFusionApiFactory

class YelpCallback {
    var apiKey : String =  "jxjG2G9MV20rNsovJ77zOtnsu665qcvWfDXHMgBWWIKdZuwhk-U2UJ0fJLnCy0Css5QrzJWAllz3l1mJgTxXPmAS2QtKvurBj1AALzoo5dKsDg63iFGZ0G8EdRHDXHYx"

    val apiFactory = YelpFusionApiFactory()
    val yelpFusionApi = apiFactory.createAPI(apiKey)
    val params = HashMap<String, String>()

    fun pullRestaraunt() : String {


// general params
        params.put("term", "indian food")
        params.put("location", "Cincinnati")
        params.put("limit", "10")

        val call = yelpFusionApi.getBusinessSearch(params)
        val response = call.execute()
        return response.toString()
    }
}