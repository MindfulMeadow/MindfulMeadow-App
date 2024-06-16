package com.group49.mindfulmeadow_app

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.group49.mindfulmeadow_app.Logging_Process.Logging_fstActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var mBtnStartLog: Button
    private lateinit var mBtnMeaning: Button
    private lateinit var homeLayout: RelativeLayout
    private lateinit var moodBackgroundManager: MoodBackgroundManager
    private lateinit var tvGreeting: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        mBtnStartLog = findViewById(R.id.btn_start_log)
        mBtnMeaning = findViewById(R.id.btn_meadow_meaning)
        homeLayout = findViewById(R.id.home_layout)
        tvGreeting = findViewById(R.id.tv_greeting)

        moodBackgroundManager = MoodBackgroundManager(this, homeLayout)

        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        val username: String = preferences.getString("username", "") ?: ""
        val userId = username

        if (username.isNotEmpty()) {
            tvGreeting.text = "Welcome, $username!"
        }

        moodBackgroundManager.updateBackgroundBasedOnMoodRecords(userId)

        mBtnStartLog.setOnClickListener {
            val preferences = PreferenceManager.getDefaultSharedPreferences(this)
            val userid = preferences.getString("username", null)
            if (userid == null) {
                showLoginPanel()
                return@setOnClickListener
            }

            val intent = Intent(this@HomeActivity, Logging_fstActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim)
        }

        mBtnMeaning.setOnClickListener {
            showMeaningPopup()
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
                    startActivity(Intent(applicationContext, Graph_WeekActivity::class.java))
                    finish()
                    true
                }
            }
            false
        }
    }

    private fun showMeaningPopup() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("What does my meadow mean?")
        builder.setMessage("The meadow in your background is a representation of the frequency and emotions related to your journal entries. \n" +
                "More frequent entries = greener background;\n" +
                "Yellow flowers = joy; orange flowers = genius; green flowers = disgust; red flowers = anger; blue flowers = sad; purple flowers = fear.\n" +
                "Earn our exclusive flower by making an entry for 7 consecutive days!")
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }

    private fun showLoginPanel() {
        startActivity(Intent(applicationContext, LogAndRegisterActivity::class.java))
        finish()
    }
}
