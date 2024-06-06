package com.group49.mindfulmeadow_app

import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.group49.mindfulmeadow_app.DataBase.Companion.recordMood
import com.group49.mindfulmeadow_app.Logging_Snd_Step_Activities.MoodRecord
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

        mBtnSave.setOnClickListener {
            val intent = Intent(this@Logging_fifthActivity, Logging_fourthActivity::class.java)
            startActivity(intent)
        }

        mBtnSave.setOnClickListener {
            val selectedDate = "${datePi.year}-${datePi.month + 1}-${datePi.dayOfMonth}"
            val formattedDate = formatDate(selectedDate)
            val moodRecord = MoodRecord(
                userId = "xxx",
                logId = "xxx",
                feeling = selectedMood,
                description = selectedItems,
                log = elaborationText,
                date = formattedDate
            )
            recordMood(moodRecord) { success ->
                if (success) {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Save Successful!")
                    builder.setMessage("Your log is saved!,with $selectedMood,${selectedItems.toString()},$elaborationText, $selectedDate")
                    builder.setPositiveButton("OK") { _, _ ->
                        val intent = Intent(this@Logging_fifthActivity, HomeActivity::class.java)
                        startActivity(intent)
                    }
                    builder.show()
                } else {
                    Toast.makeText(this, "Failed to save log.", Toast.LENGTH_SHORT).show()
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
                Toast.LENGTH_LONG).show()
        }
    }

    private fun formatDate(date: String): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val parsedDate = sdf.parse(date)
        return sdf.format(parsedDate)
    }
}