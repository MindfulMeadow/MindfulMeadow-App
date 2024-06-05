package com.group49.mindfulmeadow_app

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.group49.mindfulmeadow_app.Logging_Snd_Step_Activities.Logging_AngerActivity
import com.group49.mindfulmeadow_app.Logging_Snd_Step_Activities.Logging_DisgustActivity
import com.group49.mindfulmeadow_app.Logging_Snd_Step_Activities.Logging_FearActivity
import com.group49.mindfulmeadow_app.Logging_Snd_Step_Activities.Logging_GeniusActivity
import com.group49.mindfulmeadow_app.Logging_Snd_Step_Activities.Logging_JoyActivity
import com.group49.mindfulmeadow_app.Logging_Snd_Step_Activities.Logging_SadActivity

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
            val intent = Intent(this@Logging_fstActivity, Logging_JoyActivity::class.java)
            startActivity(intent)
        }

        mBtnGenius.setOnClickListener {
            //TODO: 记录emotion genius
            val intent = Intent(this@Logging_fstActivity, Logging_GeniusActivity::class.java)
            startActivity(intent)
        }

        mBtnDisgust.setOnClickListener {
            //TODO: 记录emotion disgust
            val intent = Intent(this@Logging_fstActivity, Logging_DisgustActivity::class.java)
            startActivity(intent)
        }

        mBtnAnger.setOnClickListener {
            //TODO: 记录emotion anger
            val intent = Intent(this@Logging_fstActivity, Logging_AngerActivity::class.java)
            startActivity(intent)
        }

        mBtnFear.setOnClickListener {
            //TODO: 记录emotion fear
            val intent = Intent(this@Logging_fstActivity, Logging_FearActivity::class.java)
            startActivity(intent)
        }

        mBtnSad.setOnClickListener {
            //TODO: 记录emotion sad
            val intent = Intent(this@Logging_fstActivity, Logging_SadActivity::class.java)
            startActivity(intent)
        }
    }
}