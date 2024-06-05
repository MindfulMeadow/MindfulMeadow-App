package com.group49.mindfulmeadow_app

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Logging_fourthActivity : AppCompatActivity() {

    private lateinit var mBtnNext: Button
    private lateinit var mBtnBackToThird: ImageView
    private lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logging_fourth)

        mBtnNext = findViewById(R.id.btn_event_next)
        mBtnBackToThird = findViewById(R.id.iv_back_to_third)
        editText = findViewById(R.id.et_1)

        mBtnBackToThird.setOnClickListener {
            val intent = Intent(this@Logging_fourthActivity, Logging_thirdActivity::class.java)
            startActivity(intent)
        }

        mBtnNext.setOnClickListener {
            val intent = Intent(this@Logging_fourthActivity, Logging_fifthActivity::class.java)
            startActivity(intent)
        }

    }
}