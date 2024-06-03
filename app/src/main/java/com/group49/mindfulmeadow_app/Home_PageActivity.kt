package com.group49.mindfulmeadow_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Home_PageActivity: AppCompatActivity() {

    private lateinit var mBtnStartLog: Button
    private lateinit var mBtnMeaning: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        mBtnStartLog = findViewById(R.id.btn_start_log)
        mBtnMeaning = findViewById(R.id.btn_meadow_meaning)

        mBtnStartLog.setOnClickListener {
            val intent = Intent(this@Home_PageActivity, Logging_1Activity::class.java)
            startActivity(intent)
        }
    }

}