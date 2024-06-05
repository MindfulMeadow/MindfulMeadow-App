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

class Logging_fifthActivity : AppCompatActivity() {

    private lateinit var mBtnSave: Button
    private lateinit var mBtnBackToFourth: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logging_fifth)

        val datePi = findViewById<DatePicker>(R.id.dp_1) as DatePicker
        val calendar :Calendar = Calendar.getInstance()

        mBtnSave = findViewById(R.id.btn_date_save)
        mBtnBackToFourth = findViewById(R.id.iv_back_to_fourth)

        mBtnSave.setOnClickListener {
            val intent = Intent(this@Logging_fifthActivity, Logging_fourthActivity::class.java)
            startActivity(intent)
        }

        mBtnSave.setOnClickListener {
            val intent = Intent(this@Logging_fifthActivity, HomeActivity::class.java)
            val builder = AlertDialog.Builder(this)
            startActivity(intent)
            builder.setTitle("Save Successful!")
            builder.setMessage("Your log is saved!")
            builder.show()
        }

        datePi.init(calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
            ){view, year, monthOfYear, dayOfMonth ->
            //TODO: Record into Database
            Toast.makeText(
                applicationContext,
                "#" + datePi.year + "-" + datePi.month + "-" + datePi.dayOfMonth + "/",
                Toast.LENGTH_LONG).show()
        }
    }
}