package com.group49.mindfulmeadow_app

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class Logging_fstActivity : AppCompatActivity() {

    private lateinit var mBtnBackToHome: ImageView

    private lateinit var mBtnJoy: ImageView
    private lateinit var mBtnGenius: ImageView
    private lateinit var mBtnDisgust: ImageView
    private lateinit var mBtnAnger: ImageView
    private lateinit var mBtnFear: ImageView
    private lateinit var mBtnSad: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loggingfst)

        mBtnBackToHome = findViewById(R.id.iv_back_to_home)

        mBtnBackToHome.setOnClickListener {
            val intent = Intent(this@Logging_fstActivity, HomeActivity::class.java)
            startActivity(intent)
        }

        mBtnJoy = findViewById(R.id.iv_joy)
        mBtnGenius = findViewById(R.id.iv_genius)
        mBtnDisgust = findViewById(R.id.iv_disgust)
        mBtnAnger = findViewById(R.id.iv_anger)
        mBtnFear = findViewById(R.id.iv_fear)
        mBtnSad = findViewById(R.id.iv_sad)

        mBtnJoy.setOnClickListener {
            //TODO: 记录emotion joy
            jumpToLoggingSndPage()
        }

        mBtnGenius.setOnClickListener {
            //TODO: 记录emotion genius
            jumpToLoggingSndPage()
        }

        mBtnDisgust.setOnClickListener {
            //TODO: 记录emotion disgust
            jumpToLoggingSndPage()
        }

        mBtnAnger.setOnClickListener {
            //TODO: 记录emotion anger
            jumpToLoggingSndPage()
        }

        mBtnFear.setOnClickListener {
            //TODO: 记录emotion fear
            jumpToLoggingSndPage()
        }

        mBtnSad.setOnClickListener {
            //TODO: 记录emotion sad
            jumpToLoggingSndPage()
        }
    }

    private fun jumpToLoggingSndPage() {
        val intent = Intent(this@Logging_fstActivity, Logging_sndActivity::class.java)
        startActivity(intent)
    }
}