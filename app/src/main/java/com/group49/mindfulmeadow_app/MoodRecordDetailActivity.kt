package com.group49.mindfulmeadow_app

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.group49.mindfulmeadow_app.DataBase.Companion.downloadFile

class MoodRecordDetailActivity : AppCompatActivity() {

    private lateinit var tvUserId: TextView
    private lateinit var tvFeeling: TextView
    private lateinit var tvDescription: TextView
    private lateinit var tvLog: TextView
    private lateinit var tvDate: TextView
    private lateinit var ivRecordImage: ImageView

    private lateinit var ivBackToHistory: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mood_record_detail)

        tvUserId = findViewById(R.id.tv_userId)
        tvFeeling = findViewById(R.id.tv_feeling)
        tvDescription = findViewById(R.id.tv_description)
        tvLog = findViewById(R.id.tv_log)
        tvDate = findViewById(R.id.tv_date)
        ivBackToHistory = findViewById(R.id.iv_back_to_history)
        ivRecordImage = findViewById(R.id.iv_record_image)

        ivBackToHistory.setOnClickListener {
            val intent = Intent(this@MoodRecordDetailActivity, HistoryActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim )
        }


        val moodRecord = intent.getSerializableExtra("selectedMoodRecord") as? MoodRecord

        moodRecord?.let {
            tvUserId.text = it.userId
            tvDate.text = it.date
            tvFeeling.text = it.feeling
            tvDescription.text = it.description.joinToString(", ")
            tvLog.text = it.log

            if (it.imageUrl.isNotEmpty()) {
                downloadFile(it.imageUrl, { file ->
                    ivRecordImage.setImageURI(Uri.fromFile(file))
                    ivRecordImage.visibility = ImageView.VISIBLE
                }, {
                    // Handle failure
                })
            } else {
                ivRecordImage.visibility = ImageView.GONE
            }
        }
    }

}
