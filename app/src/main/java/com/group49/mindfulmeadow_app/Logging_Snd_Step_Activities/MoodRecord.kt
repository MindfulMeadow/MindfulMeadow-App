package com.group49.mindfulmeadow_app.Logging_Snd_Step_Activities

data class MoodRecord(
    val userId:String,
    val logId:String,
    val feeling:String,
    val description:List<String>,
    val log:String,
    val date:String
)
