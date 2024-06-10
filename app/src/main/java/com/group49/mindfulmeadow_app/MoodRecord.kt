package com.group49.mindfulmeadow_app

import java.io.Serializable

data class MoodRecord(
    val userId:String,
    val logId:String,
    val feeling:String,
    val description:List<String>,
    val log:String,
    val date:String
) : Serializable
