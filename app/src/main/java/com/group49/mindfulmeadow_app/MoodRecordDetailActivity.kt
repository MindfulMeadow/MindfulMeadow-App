package com.group49.mindfulmeadow_app

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MoodRecordDetailActivity : AppCompatActivity() {

    private lateinit var tvUserId: TextView
    private lateinit var tvLogId: TextView
    private lateinit var tvFeeling: TextView
    private lateinit var tvDescription: TextView
    private lateinit var tvLog: TextView
    private lateinit var tvDate: TextView

    private lateinit var mBtnBackToHistory: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mood_record_detail)

        tvUserId = findViewById(R.id.tv_userId)
        tvFeeling = findViewById(R.id.tv_feeling)
        tvDescription = findViewById(R.id.tv_description)
        tvLog = findViewById(R.id.tv_log)
        tvDate = findViewById(R.id.tv_date)
        mBtnBackToHistory = findViewById(R.id.iv_back_to_history)

        mBtnBackToHistory.setOnClickListener {
            val intent = Intent(this@MoodRecordDetailActivity, HistoryActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim )
        }

        val selectedMoodRecord = intent.getSerializableExtra("selectedMoodRecord") as MoodRecord

        tvUserId.text = selectedMoodRecord.userId
        tvFeeling.text = selectedMoodRecord.feeling
        tvDescription.text = selectedMoodRecord.description.joinToString(", ")
        tvLog.text = selectedMoodRecord.log
        tvDate.text = selectedMoodRecord.date
    }
}
