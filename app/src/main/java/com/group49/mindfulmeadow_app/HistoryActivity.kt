package com.group49.mindfulmeadow_app

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.group49.mindfulmeadow_app.DataBase.Companion.getMoodRecordsAndConsume
import java.text.SimpleDateFormat
import java.util.Locale

class HistoryActivity : AppCompatActivity() {

    private lateinit var moodRecordRecyclerView: RecyclerView
    private lateinit var moodRecordAdapter: MoodRecordAdapter
    private lateinit var moodRecords: List<MoodRecord>

    private lateinit var historyLayout: RelativeLayout
    private lateinit var moodBackgroundManager: MoodBackgroundManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        moodRecordRecyclerView = findViewById(R.id.moodRecordRecyclerView)
        moodRecordRecyclerView.layoutManager = LinearLayoutManager(this)
        historyLayout = findViewById(R.id.history_layout)

        moodBackgroundManager = MoodBackgroundManager(this, historyLayout)

        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        val username: String = preferences.getString("username", "") ?: ""
        val userId = username
        moodBackgroundManager.updateBackgroundBasedOnMoodRecords(userId)

        getMoodRecordsAndConsume(userId) { records ->
            if (records != null) {
                moodRecords = sortMoodRecordsByDate(records)
                moodRecordAdapter = MoodRecordAdapter(moodRecords) { selectedRecord ->
                    val intent = Intent(this, MoodRecordDetailActivity::class.java).apply {
                        putExtra("selectedMoodRecord", selectedRecord)
                    }
                    startActivity(intent)
                    overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim )
                }
                moodRecordRecyclerView.adapter = moodRecordAdapter
            }
        }


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
            }
            false
        }

    }

    private fun sortMoodRecordsByDate(records: List<MoodRecord>): List<MoodRecord> {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return records.sortedByDescending { dateFormat.parse(it.date) }
    }
}