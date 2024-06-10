package com.group49.mindfulmeadow_app

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.group49.mindfulmeadow_app.DataBase.Companion.getMoodRecordsAndConsume
import com.group49.mindfulmeadow_app.Logging_Process.Logging_fstActivity
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Graph_WeekActivity : AppCompatActivity() {

    private lateinit var tvYear: TextView
    private lateinit var tvMonth: TextView
    private lateinit var tvWeek: TextView
    private lateinit var barChart: BarChart
    private lateinit var imageViewAnalysis: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graph_week)

        // Initialize TextViews for the time span selector
        tvYear = findViewById(R.id.tv_year)
        tvMonth = findViewById(R.id.tv_month)
        tvWeek = findViewById(R.id.tv_week)

        //Jump to corresponding page
        tvWeek.setOnClickListener {
            val intent = Intent(this@Graph_WeekActivity, Graph_WeekActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim )
        }

        tvMonth.setOnClickListener {
            val intent = Intent(this@Graph_WeekActivity, Graph_MonthActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim )
        }

        tvYear.setOnClickListener {
            val intent = Intent(this@Graph_WeekActivity, Graph_YearActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim )
        }


        // Initialize ImageViews
        barChart = findViewById(R.id.barchart_1)

        // 前一周的日期
        val dateList = getLastSevenDaysAsString()

        // get emotionScore
        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        val username: String = preferences.getString("username", "") ?: ""
        val userId = username
        getMoodRecordsAndConsume(userId) { moodRecords ->
            if (moodRecords != null) {
                val emotionScores = getEmotionScoreFromListOfMoodRecordInGivenDate(moodRecords, dateList)
                setupBarChart(dateList, emotionScores)
            } else {
                // fail do what?
            }
        }

        // Bottom Navigation View setup
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.selectedItemId = R.id.log_graph

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    startActivity(Intent(applicationContext, HomeActivity::class.java))
                    finish()
                    true
                }
                R.id.log_history -> {
                    startActivity(Intent(applicationContext, HistoryActivity::class.java))
                    finish()
                    true
                }
                R.id.log_graph -> return@setOnItemSelectedListener true
                R.id.ai_analysis -> {
                    startActivity(Intent(applicationContext, AnalysisActivity::class.java))
                    finish()
                    true
                }
            }
            false
        }
    }

    private fun getLastSevenDaysAsString(): List<String> {
        val today = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return (0..6).map { today.minusDays(it.toLong()).format(formatter) }
    }

    private fun getEmotionScoreFromListOfMoodRecordInGivenDate(moodRecords: List<MoodRecord>, dates: List<String>): List<Float> {
        val emotionScores = mutableListOf<Float>()

        for (date in dates) {
            val recordsOnDate = moodRecords.filter { it.date == date }

            val score = calculateEmotionScore(recordsOnDate)
            emotionScores.add(score)
        }

        return emotionScores
    }

    private fun calculateEmotionScore(records: List<MoodRecord>): Float {
        if (records.isEmpty()) return 0f

        val scoreMap = mapOf(
            "Fear: Terrified" to 1.0f,
            "Fear: Insignificant" to 2.0f,
            "Fear: Overwhelmed" to 3.0f,
            "Fear: Threatened" to 4.0f,
            "Fear: Vulnerable" to 5.0f,
            "Fear: Anxious" to 6.0f,
            "Fear: Insecure" to 7.0f,
            "Fear: Worried" to 8.0f,
            "Sad: Depressed" to 9.0f,
            "Sad: Heartbroken" to 10.0f,
            "Sad: Frustrated" to 11.0f,
            "Sad: Isolated" to 12.0f,
            "Sad: Lonely" to 13.0f,
            "Sad: Tired" to 14.0f,
            "Sad: Empty" to 15.0f,
            "Sad: Bored" to 16.0f,
            "Anger: Frustrated" to 17.0f,
            "Anger: Aggressive" to 18.0f,
            "Anger: Envious" to 19.0f,
            "Anger: Resentful" to 20.0f,
            "Anger: Irritated" to 21.0f,
            "Anger: Defensive" to 22.0f,
            "Anger: Protective" to 23.0f,
            "Anger: Skeptical" to 24.0f,
            "Disgust: Ashamed" to 25.0f,
            "Disgust: Humiliated" to 26.0f,
            "Disgust: Embarrassed" to 27.0f,
            "Disgust: Guilty" to 28.0f,
            "Disgust: Uncomfortable" to 29.0f,
            "Disgust: Nauseous" to 30.0f,
            "Disgust: Judgemental" to 31.0f,
            "Disgust: Self-conscious" to 32.0f,
            "Joy: Calm" to 33.0f,
            "Joy: Kind" to 34.0f,
            "Joy: Curious" to 35.0f,
            "Joy: Free" to 36.0f,
            "Joy: Confident" to 37.0f,
            "Joy: Moved" to 38.0f,
            "Joy: Brave" to 39.0f,
            "Joy: Proud" to 40.0f,
            "Genius: Inspired" to 41.0f,
            "Genius: In Flow" to 42.0f,
            "Genius: Confident" to 43.0f,
            "Genius: Focused" to 44.0f,
            "Genius: Creative" to 45.0f,
            "Genius: Passionate" to 46.0f,
            "Genius: Challenged" to 47.0f,
            "Genius: Motivated" to 48.0f
        )

        val defaultScores = mapOf(
            "Fear" to 1.0f,
            "Sad" to 9.0f,
            "Anger" to 17.0f,
            "Disgust" to 25.0f,
            "Joy" to 33.0f,
            "Genius" to 41.0f
        )

        val totalScore = records.map { record ->
            scoreMap[record.feeling] ?: run {
                val key = record.feeling.split(":").first()
                defaultScores[key] ?: 0.0f
            }
        }.sum()

        return totalScore / records.size
    }

    private fun setupBarChart(dateList: List<String>, emotionScores: List<Float>) {
        val barEntries = ArrayList<BarEntry>()

        for (i in emotionScores.indices) {
            barEntries.add(BarEntry(i.toFloat(), emotionScores[i]))
        }

        val barDataSet = BarDataSet(barEntries, "Emotion Scores")
        barDataSet.colors = barEntries.map { entry ->
            when (entry.y.toInt()) {
                in 1..8 -> getColor(R.color.purple)
                in 9..16 -> getColor(R.color.blue)
                in 17..24 -> getColor(R.color.red)
                in 25..32 -> getColor(R.color.green)
                in 33..40 -> getColor(R.color.yellow)
                in 41..48 -> getColor(R.color.orange)
                else -> resources.getColor(R.color.gray)
            }
        }
        val barData = BarData(barDataSet)

        barChart.data = barData
        barChart.description.isEnabled = false
        barChart.setFitBars(true)

        val xAxis = barChart.xAxis
        xAxis.valueFormatter = MonthDayFormatter(dateList)
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.granularity = 1f
        xAxis.labelCount = dateList.size

        barChart.axisRight.isEnabled = false
        barChart.invalidate() // refresh
    }

    class MonthDayFormatter(private val dates: List<String>) : IndexAxisValueFormatter() {
        private val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        private val outputFormatter = DateTimeFormatter.ofPattern("MM-dd")

        override fun getFormattedValue(value: Float): String {
            val index = value.toInt()
            return if (index >= 0 && index < dates.size) {
                val date = LocalDate.parse(dates[index], inputFormatter)
                date.format(outputFormatter)
            } else {
                ""
            }
        }
    }
}
