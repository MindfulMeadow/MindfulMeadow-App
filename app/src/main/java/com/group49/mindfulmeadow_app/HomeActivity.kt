package com.group49.mindfulmeadow_app

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button
import android.widget.RelativeLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.aallam.openai.api.chat.ChatCompletion
import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.api.http.Timeout
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.group49.mindfulmeadow_app.Logging_Process.Logging_fstActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class HomeActivity : AppCompatActivity() {

    private lateinit var mBtnStartLog: Button
    private lateinit var mBtnMeaning: Button
    private lateinit var homeLayout: RelativeLayout
    private lateinit var moodBackgroundManager: MoodBackgroundManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        mBtnStartLog = findViewById(R.id.btn_start_log)
        mBtnMeaning = findViewById(R.id.btn_meadow_meaning)
        homeLayout = findViewById(R.id.home_layout)

        moodBackgroundManager = MoodBackgroundManager(this, homeLayout)

        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        val username: String = preferences.getString("username", "") ?: ""
        val userId = username
        moodBackgroundManager.updateBackgroundBasedOnMoodRecords(userId)

        mBtnStartLog.setOnClickListener {

            val preferences = PreferenceManager.getDefaultSharedPreferences(this)
            val userid = preferences.getString("username", null)
            if(userid == null){
                showLoginPanel()
                return@setOnClickListener
            }

            val intent = Intent(this@HomeActivity, Logging_fstActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim )

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
                R.id.ai_analysis -> {
                    startActivity(Intent(applicationContext, AnalysisActivity::class.java))
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
        builder.setMessage("This meadow is a visual representation of your emotional state: flowers grow when your emotional state is healthy; grass wither when you need to take better care of your emotional state.") // Replace with your actual text
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