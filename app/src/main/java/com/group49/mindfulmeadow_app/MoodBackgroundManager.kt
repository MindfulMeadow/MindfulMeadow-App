package com.group49.mindfulmeadow_app

import android.content.Context
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.TransitionDrawable
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import com.group49.mindfulmeadow_app.DataBase.Companion.getMoodRecordsAndConsume
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MoodBackgroundManager(private val context: Context, private val rootLayout: RelativeLayout) {

    companion object {
        private const val TAG = "MoodBackgroundManager"
    }

    fun updateBackgroundBasedOnMoodRecords(userId: String) {
        getMoodRecordsAndConsume(userId) { records ->
            if (records != null) {
                val lastSevenDaysRecords = getLastSevenDaysRecords(records)
                val dailyScores = lastSevenDaysRecords.map { calculateEmotionScore(it.value) }
                val allSevenDaysHaveRecords = checkAllSevenDaysHaveRecords(lastSevenDaysRecords)
                val recordCount = lastSevenDaysRecords.values.flatten().size
                updateBackground(recordCount, dailyScores, allSevenDaysHaveRecords)
            } else {
                updateBackground(0, emptyList(), false)
            }
        }
    }

    private fun getLastSevenDaysRecords(records: List<MoodRecord>): Map<String, List<MoodRecord>> {
        val today = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val lastSevenDays = (0..6).map { today.minusDays(it.toLong()).format(formatter) }

        return records
            .filter { it.date in lastSevenDays }
            .groupBy { it.date }
    }

    private fun checkAllSevenDaysHaveRecords(lastSevenDaysRecords: Map<String, List<MoodRecord>>): Boolean {
        val today = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val lastSevenDays = (0..6).map { today.minusDays(it.toLong()).format(formatter) }

        return lastSevenDays.all { it in lastSevenDaysRecords.keys }
    }

    public fun calculateEmotionScore(records: List<MoodRecord>): Float {
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

    private fun updateBackground(recordCount: Int, dailyScores: List<Float>, allSevenDaysHaveRecords: Boolean) {
        val baseBackground = when {
            recordCount < 1  -> R.drawable.grass_1
            recordCount < 3 -> R.drawable.grass_2
            recordCount < 5 -> R.drawable.grass_3
            recordCount < 7 -> R.drawable.grass_4
            else -> R.drawable.grass_5
        }

        val layers = mutableListOf(ContextCompat.getDrawable(context, baseBackground))

        val scoreToDrawableMap = mapOf(
            (1..8) to R.drawable.flower_fear,
            (9..16) to R.drawable.flower_sad,
            (17..24) to R.drawable.flower_anger,
            (25..32) to R.drawable.flower_disgust,
            (33..40) to R.drawable.flower_joy,
            (41..48) to R.drawable.flower_genious
        )

        val addedLayers = mutableSetOf<Int>()

        for (score in dailyScores) {
            for ((range, drawableRes) in scoreToDrawableMap) {
                if (score.toInt() in range && !addedLayers.contains(drawableRes)) {
                    layers.add(ContextCompat.getDrawable(context, drawableRes))
                    addedLayers.add(drawableRes)
                }
            }
        }

        if (allSevenDaysHaveRecords) {
            layers.add(ContextCompat.getDrawable(context, R.drawable.flower_exclusive))
        }

        layers.add(ContextCompat.getDrawable(context, R.drawable.white_filter))

        val layerDrawable = LayerDrawable(layers.toTypedArray())
        val currentBackground = rootLayout.background

        if (currentBackground is LayerDrawable) {
            rootLayout.background = layerDrawable
        } else {
            val transitionDrawable = TransitionDrawable(arrayOf(currentBackground, layerDrawable))
            rootLayout.background = transitionDrawable
            transitionDrawable.startTransition(1000)
        }
    }
}

