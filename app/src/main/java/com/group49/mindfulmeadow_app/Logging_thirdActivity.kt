package com.group49.mindfulmeadow_app

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Logging_thirdActivity : AppCompatActivity() {

    private lateinit var mBtnBackToSnd: ImageView
    private lateinit var mBtnNext: Button

    private lateinit var friend: RadioButton
    private lateinit var romantic_relationship: RadioButton
    private lateinit var family: RadioButton
    private lateinit var pets: RadioButton
    private lateinit var work: RadioButton
    private lateinit var study: RadioButton
    private lateinit var chores: RadioButton
    private lateinit var social_media: RadioButton
    private lateinit var strangers: RadioButton
    private lateinit var crime: RadioButton
    private lateinit var news: RadioButton
    private lateinit var politics: RadioButton
    private lateinit var health: RadioButton
    private lateinit var weather: RadioButton
    private lateinit var sports: RadioButton
    private lateinit var leisure: RadioButton
    private lateinit var celebrity: RadioButton
    private lateinit var finance: RadioButton
    private lateinit var accident: RadioButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logging_third)

        initialWidgets()

        mBtnBackToSnd.setOnClickListener {
            val intent = Intent(this@Logging_thirdActivity, Logging_fstActivity::class.java)
            startActivity(intent)
        }

        mBtnNext.setOnClickListener {
            val intent = Intent(this@Logging_thirdActivity, Logging_fourthActivity::class.java)
            startActivity(intent)
        }

        updateRadioGroup(friend)
        }

    private fun initialWidgets() {
        mBtnBackToSnd = findViewById(R.id.iv_back_to_snd)
        mBtnNext = findViewById(R.id.btn_event_next)

        friend = findViewById(R.id.rb_friend)
        romantic_relationship = findViewById(R.id.rb_romantic_relationship)
        family = findViewById(R.id.rb_family)
        pets = findViewById(R.id.rb_pets)
        work = findViewById(R.id.rb_work)
        study = findViewById(R.id.rb_study)
        chores = findViewById(R.id.rb_chores)
        social_media = findViewById(R.id.rb_social_media)
        strangers = findViewById(R.id.rb_strangers)
        crime = findViewById(R.id.rb_crime)
        news = findViewById(R.id.rb_news)
        politics = findViewById(R.id.rb_politics)
        weather = findViewById(R.id.rb_weather)
        health = findViewById(R.id.rb_health)
        sports = findViewById(R.id.rb_sports)
        leisure = findViewById(R.id.rb_leisure)
        celebrity = findViewById(R.id.rb_celebrity)
        finance = findViewById(R.id.rb_finance)
        accident = findViewById(R.id.rb_accident)
    }


    private fun updateRadioGroup(selected: RadioButton) {
        friend.background = ContextCompat.getDrawable(applicationContext, R.drawable.radio_off)
        romantic_relationship.background = ContextCompat.getDrawable(applicationContext, R.drawable.radio_off)
        family.background = ContextCompat.getDrawable(applicationContext, R.drawable.radio_off)
        pets.background = ContextCompat.getDrawable(applicationContext, R.drawable.radio_off)
        work.background = ContextCompat.getDrawable(applicationContext, R.drawable.radio_off)
        study.background = ContextCompat.getDrawable(applicationContext, R.drawable.radio_off)
        chores.background = ContextCompat.getDrawable(applicationContext, R.drawable.radio_off)
        social_media.background = ContextCompat.getDrawable(applicationContext, R.drawable.radio_off)
        strangers.background = ContextCompat.getDrawable(applicationContext, R.drawable.radio_off)
        crime.background = ContextCompat.getDrawable(applicationContext, R.drawable.radio_off)
        news.background = ContextCompat.getDrawable(applicationContext, R.drawable.radio_off)
        politics.background = ContextCompat.getDrawable(applicationContext, R.drawable.radio_off)
        weather.background = ContextCompat.getDrawable(applicationContext, R.drawable.radio_off)
        health.background = ContextCompat.getDrawable(applicationContext, R.drawable.radio_off)
        sports.background = ContextCompat.getDrawable(applicationContext, R.drawable.radio_off)
        leisure.background = ContextCompat.getDrawable(applicationContext, R.drawable.radio_off)
        celebrity.background = ContextCompat.getDrawable(applicationContext, R.drawable.radio_off)
        finance.background = ContextCompat.getDrawable(applicationContext, R.drawable.radio_off)
        accident.background = ContextCompat.getDrawable(applicationContext, R.drawable.radio_off)

        selected.background = ContextCompat.getDrawable(applicationContext, R.drawable.radio_on)

    }

    fun radioTapped(view: View) {
        val selectedID: Int = view.id

        if (selectedID == R.id.rb_friend) {
            updateRadioGroup(friend)
        } else if (selectedID == R.id.rb_romantic_relationship) {
            updateRadioGroup(romantic_relationship)
        } else if (selectedID == R.id.rb_family) {
            updateRadioGroup(family)
        } else if (selectedID == R.id.rb_pets) {
            updateRadioGroup(pets)
        } else if (selectedID == R.id.rb_work) {
            updateRadioGroup(work)
        } else if (selectedID == R.id.rb_study) {
            updateRadioGroup(study)
        } else if (selectedID == R.id.rb_chores) {
            updateRadioGroup(chores)
        } else if (selectedID == R.id.rb_social_media) {
            updateRadioGroup(social_media)
        } else if (selectedID == R.id.rb_strangers) {
            updateRadioGroup(strangers)
        } else if (selectedID == R.id.rb_crime) {
            updateRadioGroup(crime)
        } else if (selectedID == R.id.rb_news) {
            updateRadioGroup(news)
        } else if (selectedID == R.id.rb_politics) {
            updateRadioGroup(politics)
        } else if (selectedID == R.id.rb_health) {
            updateRadioGroup(health)
        } else if (selectedID == R.id.rb_weather) {
            updateRadioGroup(weather)
        } else if (selectedID == R.id.rb_sports) {
            updateRadioGroup(sports)
        } else if (selectedID == R.id.rb_leisure) {
            updateRadioGroup(leisure)
        } else if (selectedID == R.id.rb_celebrity) {
            updateRadioGroup(celebrity)
        } else if (selectedID == R.id.rb_finance) {
            updateRadioGroup(finance)
        } else if (selectedID == R.id.rb_accident) {
            updateRadioGroup(accident)
        }
    }
}