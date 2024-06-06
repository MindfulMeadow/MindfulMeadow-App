package com.group49.mindfulmeadow_app.Logging_Process

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.group49.mindfulmeadow_app.R

class Logging_fourthActivity : AppCompatActivity() {

    private lateinit var mBtnNext: Button
    private lateinit var mBtnBackToThird: ImageView
    private lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logging_fourth)

        val selectedMood = intent.getStringExtra("selectedMood")
        val selectedItems = intent.getStringArrayListExtra("selectedItems")

        mBtnNext = findViewById(R.id.btn_event_next)
        mBtnBackToThird = findViewById(R.id.iv_back_to_third)
        editText = findViewById(R.id.et_1)

        mBtnBackToThird.setOnClickListener {
            val intent = Intent(this@Logging_fourthActivity, Logging_thirdActivity::class.java)
            startActivity(intent)
        }

        mBtnNext.setOnClickListener {
            val elaborationText = editText.text.toString()
            val intent = Intent(this@Logging_fourthActivity, Logging_fifthActivity::class.java).apply {
                putExtra("elaborationText", elaborationText)
                putExtra("selectedMood", selectedMood)
                putStringArrayListExtra("selectedItems", selectedItems)
            }
            startActivity(intent)
        }

    }
}