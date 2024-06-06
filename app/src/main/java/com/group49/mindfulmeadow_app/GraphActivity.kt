package com.group49.mindfulmeadow_app

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class GraphActivity : AppCompatActivity() {

    private lateinit var tvYear: TextView
    private lateinit var tvMonth: TextView
    private lateinit var tvWeek: TextView
    private lateinit var imageViewGraph: ImageView
    private lateinit var imageViewAnalysis: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graph)

        // Initialize TextViews for the time span selector
        tvYear = findViewById(R.id.tv_year)
        tvMonth = findViewById(R.id.tv_month)
        tvWeek = findViewById(R.id.tv_week)

        val timeSpanTextViews = listOf(tvYear, tvMonth, tvWeek)

        // Set onClickListener for each TextView
        timeSpanTextViews.forEach { textView ->
            textView.setOnClickListener {
                updateSelectedTimeSpan(textView)
            }
        }

        // Initialize ImageViews
        imageViewGraph = findViewById(R.id.imageView_graph)
        imageViewAnalysis = findViewById(R.id.imageView_Analysis)

        // Set default selection to Week
        updateSelectedTimeSpan(tvWeek)

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

    private fun updateSelectedTimeSpan(selectedTextView: TextView) {
        val timeSpanTextViews = listOf(tvYear, tvMonth, tvWeek)
        timeSpanTextViews.forEach { textView ->
            textView.isSelected = textView == selectedTextView
            textView.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    if (textView.isSelected) android.R.color.holo_green_light else android.R.color.darker_gray
                )
            )
            textView.setTextColor(
                ContextCompat.getColor(
                    this,
                    if (textView.isSelected) android.R.color.black else android.R.color.white
                )
            )
        }
        // Change the graph image based on the selected option
        when (selectedTextView) {
            tvYear -> {
                imageViewGraph.setImageResource(R.drawable.psudo_year_graph)
                imageViewAnalysis.setImageResource(R.drawable.pseudo_year_analysis)
            }
            tvMonth -> {
                imageViewGraph.setImageResource(R.drawable.psudo_month_graph)
                imageViewAnalysis.setImageResource(R.drawable.pseudo_month_analysis)
            }
            tvWeek -> {
                imageViewGraph.setImageResource(R.drawable.psudo_week_graph)
                imageViewAnalysis.setImageResource(R.drawable.pseudo_week_analysis)
            }
        }
    }
}
