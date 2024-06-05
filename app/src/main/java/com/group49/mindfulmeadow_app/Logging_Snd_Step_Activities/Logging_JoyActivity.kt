package com.group49.mindfulmeadow_app.Logging_Snd_Step_Activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.group49.mindfulmeadow_app.Logging_fstActivity
import com.group49.mindfulmeadow_app.Logging_thirdActivity
import com.group49.mindfulmeadow_app.R

class Logging_JoyActivity : AppCompatActivity() {

    private lateinit var mRG1: RadioGroup
    private lateinit var mBtnBackToFst: ImageView
    private lateinit var mBtnNext: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logging_joy)

        mBtnBackToFst = findViewById(R.id.iv_back_to_fst)
        mRG1 = findViewById(R.id.rg_joy)
        mBtnNext = findViewById(R.id.btn_joy_next)

        mBtnBackToFst.setOnClickListener {
            val intent = Intent(this@Logging_JoyActivity, Logging_fstActivity::class.java)
            startActivity(intent)
        }

        mBtnNext.setOnClickListener {
            val intent = Intent(this@Logging_JoyActivity, Logging_thirdActivity::class.java)
            startActivity(intent)
        }

        mRG1.setOnCheckedChangeListener { group, checkedId ->
            val radioButton = group.findViewById<RadioButton>(checkedId)

            // Here We get the text of the button, such as Calm, Kind...
            // TODO: Record it into database
            val text = radioButton.text
        }
    }
}