package com.example.mobiletask.data.response

import java.io.Serializable

data class ModulesApiResponse(
    val statusCode:Int,
    val statusMessage:String?,
    val data: ModulesDataResponse

): Serializable