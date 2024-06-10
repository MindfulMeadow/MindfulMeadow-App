package com.group49.mindfulmeadow_app

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MoodRecordDetailActivity : AppCompatActivity() {

    private lateinit var tvUserId: TextView
    private lateinit var tvLogId: TextView
    private lateinit var tvFeeling: TextView
    private lateinit var tvDescription: TextView
    private lateinit var tvLog: TextView
    private lateinit var tvDate: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mood_record_detail)

        tvUserId = findViewById(R.id.tv_userId)
        tvLogId = findViewById(R.id.tv_logId)
        tvFeeling = findViewById(R.id.tv_feeling)
        tvDescription = findViewById(R.id.tv_description)
        tvLog = findViewById(R.id.tv_log)
        tvDate = findViewById(R.id.tv_date)

        val selectedMoodRecord = intent.getSerializableExtra("selectedMoodRecord") as MoodRecord

        tvUserId.text = selectedMoodRecord.userId
        tvLogId.text = selectedMoodRecord.logId
        tvFeeling.text = selectedMoodRecord.feeling
        tvDescription.text = selectedMoodRecord.description.joinToString(", ")
        tvLog.text = selectedMoodRecord.log
        tvDate.text = selectedMoodRecord.date
    }
}
