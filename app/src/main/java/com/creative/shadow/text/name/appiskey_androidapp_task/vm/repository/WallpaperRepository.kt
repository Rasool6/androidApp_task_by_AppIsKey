package com.creative.shadow.text.name.appiskey_androidapp_task.vm.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.creative.shadow.text.name.appiskey_androidapp_task.model.WallpaperModel2
import com.creative.shadow.text.name.appiskey_androidapp_task.utils.Constants
import com.creative.shadow.text.name.appiskey_androidapp_task.utils.Constants.pixaBayKey1
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException
import java.util.concurrent.TimeUnit

class WallpaperRepository {


    fun fetchWallpaper(category: String): MutableLiveData<List<WallpaperModel2>?> {

        val liveData = MutableLiveData<List<WallpaperModel2>?>()

        val client = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()


        val request = Request.Builder()
            .url("https://pixabay.com/api/?key=$pixaBayKey1&q=$category&image_type=photo&pretty=true")
             .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("varMsg", "onFailure: ${e.message}")
                Constants.isSuccessful = "Failed"
                liveData.postValue(null)
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    Constants.isSuccessful = "successful"
                    try {
                        // Trim the response
                        val jsonResponse = response.body?.string()?.trim()
                        val dataList = mutableListOf<WallpaperModel2>()
                        Log.d("varMsg", "onResponse: $jsonResponse ")

                        val json = jsonResponse?.let { JSONObject(it) }
//                     hits array
                        val jsonArray = json?.getJSONArray("hits")

//                    get data from array one by one
                        for (data in 0 until jsonArray?.length()!!) {
                            val id = jsonArray.getJSONObject(data).getInt("id")
                            val wallpaperUrl = jsonArray.getJSONObject(data).getString("largeImageURL")
                            val wallpaperUser = jsonArray.getJSONObject(data).getString("user")
                            val wallpaperTags = jsonArray.getJSONObject(data).getString("tags")
                            val wallpaperLikes = jsonArray.getJSONObject(data).getInt("likes")

                            dataList.add(
                                WallpaperModel2(
                                    id,
                                    wallpaperUrl,
                                    wallpaperUser,
                                    wallpaperTags,
                                    wallpaperLikes
                                )
                            )
                            Log.d("varMsg", "wallpaperUrl: $wallpaperUrl ")
                            Log.d("varMsg", "user: $wallpaperUser ")
                            Log.d("varMsg", "wallpaperTags: $wallpaperTags ")
                            Log.d("varMsg", "wallpaperLikes: $wallpaperLikes ")
                        }

//                        post dataList in livedata  obj
                        liveData.postValue(dataList)


                    } catch (e: Exception) {
                        Constants.isSuccessful = "Error"
                        Log.d("varMsg", "Error parsing JSON response ${e.message}")
                        liveData.postValue(null)
                    }
                } else {
                    Constants.isSuccessful = "noResponse"
                    Log.d("varMsg", "onResponse: Failed")
                    liveData.postValue(null)
                }
            }
        })

        return liveData
    }

}