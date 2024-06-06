package com.group49.mindfulmeadow_app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class HistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)


        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.selectedItemId = R.id.log_history

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    startActivity(Intent(applicationContext, HomeActivity::class.java))
                    finish()
                    true
                }
                R.id.log_history -> return@setOnItemSelectedListener true
                R.id.log_graph -> {
                    startActivity(Intent(applicationContext, Graph_WeekActivity::class.java))
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