package com.group49.mindfulmeadow_app.Logging_Process.Logging_Snd_Step_Activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.group49.mindfulmeadow_app.Logging_Process.Logging_fstActivity
import com.group49.mindfulmeadow_app.Logging_Process.Logging_thirdActivity
import com.group49.mindfulmeadow_app.R

class Logging_SadActivity : AppCompatActivity() {

    private lateinit var mRG1: RadioGroup
    private lateinit var mBtnBackToFst: ImageView
    private lateinit var mBtnNext: Button
    private lateinit var et_others: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logging_sad)

        mBtnBackToFst = findViewById(R.id.iv_back_to_fst)
        mRG1 = findViewById(R.id.rg_sad)
        mBtnNext = findViewById(R.id.btn_sad_next)
        et_others = findViewById(R.id.et_sad)

        mBtnBackToFst.setOnClickListener {
            val intent = Intent(this@Logging_SadActivity, Logging_fstActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim )
        }

        mBtnNext.setOnClickListener {
            val selectedRadioButtonId = mRG1.checkedRadioButtonId
            val othersText = et_others.text.toString()
            if (selectedRadioButtonId != -1 || othersText.isNotEmpty()) {
                val selectedText = if (othersText.isNotEmpty()) {
                    othersText
                } else {
                    val selectedRadioButton = findViewById<RadioButton>(selectedRadioButtonId)
                    selectedRadioButton.text.toString()
                }
                val intent = Intent(this@Logging_SadActivity, Logging_thirdActivity::class.java).apply {
                    putExtra("selectedMood", "Sad: " + selectedText)
                }
                startActivity(intent)
                overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim)
            } else {
                Toast.makeText(this, "Please select a mood.", Toast.LENGTH_SHORT).show()
            }
        }

        mRG1.setOnCheckedChangeListener { group, checkedId ->
            val radioButton = group.findViewById<RadioButton>(checkedId)

            // Here We get the text of the button, such as Calm, Kind...
            // TODO: Record it into database
            val text = radioButton.text
        }
    }
}