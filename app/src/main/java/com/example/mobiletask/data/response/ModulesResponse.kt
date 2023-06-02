package com.example.mobiletask.data.response

import java.io.Serializable

data class ModulesResponse(
    val modules: ArrayList<ModuleData>
) : Serializable
