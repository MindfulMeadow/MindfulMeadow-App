package com.group49.mindfulmeadow_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    private lateinit var mBtnStartLog: Button
    private lateinit var mBtnMeaning: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        mBtnStartLog = findViewById(R.id.btn_start_log)
        mBtnMeaning = findViewById(R.id.btn_meadow_meaning)

        mBtnStartLog.setOnClickListener {
            val intent = Intent(this@HomeActivity, Logging_fstActivity::class.java)
            startActivity(intent)
        }


        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.selectedItemId = R.id.home

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> return@setOnItemSelectedListener true
                R.id.log_history -> {
                    startActivity(Intent(applicationContext, HistoryActivity::class.java))
                    finish()
                    true
                }
                R.id.log_graph -> {
                    startActivity(Intent(applicationContext, GraphActivity::class.java))
                    finish()
                    true
                }
                R.id.ai_analysis -> {
                    startActivity(Intent(applicationContext, AnalysisActivity::class.java))
                    finish()
                    true
                }
            }
            false
        }


    }
}