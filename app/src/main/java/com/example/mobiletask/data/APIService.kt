package com.example.mobiletask.data

import com.example.mobiletask.data.response.ModulesApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("/api/imperium/get_class_icons")
    fun fetchModules(@Query("schoolID") schoolId :String,@Query("username") username:String): Call<ModulesApiResponse>
}