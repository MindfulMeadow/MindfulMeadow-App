package com.group49.mindfulmeadow_app.Logging_Process.Logging_Snd_Step_Activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.group49.mindfulmeadow_app.Logging_Process.Logging_fstActivity
import com.group49.mindfulmeadow_app.Logging_Process.Logging_thirdActivity
import com.group49.mindfulmeadow_app.R

class Logging_AngerActivity : AppCompatActivity() {

    private lateinit var mRG1: RadioGroup
    private lateinit var mBtnBackToFst: ImageView
    private lateinit var mBtnNext: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logging_anger)

        mBtnBackToFst = findViewById(R.id.iv_back_to_fst)
        mRG1 = findViewById(R.id.rg_anger)
        mBtnNext = findViewById(R.id.btn_anger_next)

        mBtnBackToFst.setOnClickListener {
            val intent = Intent(this@Logging_AngerActivity, Logging_fstActivity::class.java)
            startActivity(intent)
        }

        mBtnNext.setOnClickListener {
            val selectedRadioButtonId = mRG1.checkedRadioButtonId
            if (selectedRadioButtonId != -1) {
                val selectedRadioButton = findViewById<RadioButton>(selectedRadioButtonId)
                val selectedText = selectedRadioButton.text.toString()
                val intent = Intent(this@Logging_AngerActivity, Logging_thirdActivity::class.java).apply {
                    putExtra("selectedMood", "Anger: " + selectedText)
                }
                startActivity(intent)
            } else {
                Toast.makeText(this, "Please select a mood.", Toast.LENGTH_SHORT).show()
            }
        }

        mRG1.setOnCheckedChangeListener { group, checkedId ->
            val radioButton = group.findViewById<RadioButton>(checkedId)
            val text = radioButton.text
        }
    }
}