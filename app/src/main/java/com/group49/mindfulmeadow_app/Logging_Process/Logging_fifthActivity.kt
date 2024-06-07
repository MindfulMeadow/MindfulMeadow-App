package com.group49.mindfulmeadow_app.Logging_Process

import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button
import android.widget.DatePicker
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.group49.mindfulmeadow_app.DataBase.Companion.recordMood
import com.group49.mindfulmeadow_app.HomeActivity
import com.group49.mindfulmeadow_app.MoodRecord
import com.group49.mindfulmeadow_app.R
import java.text.SimpleDateFormat
import java.util.Locale

class Logging_fifthActivity : AppCompatActivity() {

    private lateinit var mBtnSave: Button
    private lateinit var mBtnBackToFourth: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logging_fifth)

        val elaborationText = intent.getStringExtra("elaborationText") ?: ""
        val selectedMood = intent.getStringExtra("selectedMood") ?: ""
        val selectedItems = intent.getStringArrayListExtra("selectedItems") ?: arrayListOf()

        val datePi = findViewById<DatePicker>(R.id.dp_1) as DatePicker
        val calendar :Calendar = Calendar.getInstance()

        mBtnSave = findViewById(R.id.btn_date_save)
        mBtnBackToFourth = findViewById(R.id.iv_back_to_fourth)

        mBtnBackToFourth.setOnClickListener {
            val intent = Intent(this@Logging_fifthActivity, Logging_fourthActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim )
        }

        mBtnSave.setOnClickListener {
            val preferences = PreferenceManager.getDefaultSharedPreferences(this)
            val username = preferences.getString("username", null)
            val selectedDate = "${datePi.year}-${datePi.month + 1}-${datePi.dayOfMonth}"
            val formattedDate = formatDate(selectedDate)
            val logId = generateLogId()
            val moodRecord = username?.let { it1 ->
                MoodRecord(
                    userId = it1,
                    logId = logId,
                    feeling = selectedMood,
                    description = selectedItems,
                    log = elaborationText,
                    date = formattedDate
                )
            }
            if (moodRecord != null) {
                recordMood(moodRecord) { success ->
                    if (success) {
                        val builder = AlertDialog.Builder(this)
                        builder.setTitle("Save Successful!")
                        builder.setMessage("Your log is saved!,with $selectedMood,${selectedItems.toString()},$elaborationText, $selectedDate, $logId")
                        builder.setPositiveButton("OK") { _, _ ->
                            val intent = Intent(this@Logging_fifthActivity, HomeActivity::class.java)
                            startActivity(intent)
                            overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim)
                        }
                        builder.show()
                    } else {
                        Toast.makeText(this, "Failed to save log.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        datePi.init(calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
            ){view, year, monthOfYear, dayOfMonth ->
            Toast.makeText(
                applicationContext,
                "#" + datePi.year + "-" + datePi.month + "-" + datePi.dayOfMonth + "/",
                Toast.LENGTH_LONG)
        }
    }

    private fun generateLogId(): String {
        val timestamp = System.currentTimeMillis()
        return "log-$timestamp"
    }

    private fun formatDate(date: String): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val parsedDate = sdf.parse(date)
        return sdf.format(parsedDate)
    }
}