package com.group49.mindfulmeadow_app

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Logging_thirdActivity : AppCompatActivity() {

    private lateinit var mBtnBackToSnd: ImageView
    private lateinit var mBtnNext: Button

    private lateinit var friend: CheckBox
    private lateinit var romantic_relationship: CheckBox
    private lateinit var family: CheckBox
    private lateinit var pets: CheckBox
    private lateinit var work: CheckBox
    private lateinit var study: CheckBox
    private lateinit var chores: CheckBox
    private lateinit var social_media: CheckBox
    private lateinit var strangers: CheckBox
    private lateinit var crime: CheckBox
    private lateinit var news: CheckBox
    private lateinit var politics: CheckBox
    private lateinit var health: CheckBox
    private lateinit var weather: CheckBox
    private lateinit var sports: CheckBox
    private lateinit var leisure: CheckBox
    private lateinit var celebrity: CheckBox
    private lateinit var finance: CheckBox
    private lateinit var accident: CheckBox
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logging_third)

        initialWidgets()

        mBtnBackToSnd = findViewById(R.id.iv_back_to_snd)
        mBtnNext = findViewById(R.id.btn_event_next)

        mBtnBackToSnd.setOnClickListener {
            val intent = Intent(this@Logging_thirdActivity, Logging_fstActivity::class.java)
            startActivity(intent)
        }

        val selectedMood = intent.getStringExtra("selectedMood")

        mBtnNext.setOnClickListener {
            val selectedItems = getSelectedCheckBoxes()
            val selectedMoodText = selectedMood ?: ""

            val intent = Intent(this@Logging_thirdActivity, Logging_fourthActivity::class.java).apply {
                putExtra("selectedMood", selectedMoodText)
                putStringArrayListExtra("selectedItems", ArrayList(selectedItems))
            }
            startActivity(intent)
        }

        }

    private fun initialWidgets() {

        friend = findViewById(R.id.cb_friend)
        romantic_relationship = findViewById(R.id.cb_romantic_relationship)
        family = findViewById(R.id.cb_family)
        pets = findViewById(R.id.cb_pets)
        work = findViewById(R.id.cb_work)
        study = findViewById(R.id.cb_study)
        chores = findViewById(R.id.cb_chores)
        social_media = findViewById(R.id.cb_social_media)
        strangers = findViewById(R.id.cb_strangers)
        crime = findViewById(R.id.cb_crime)
        news = findViewById(R.id.cb_news)
        politics = findViewById(R.id.cb_politics)
        weather = findViewById(R.id.cb_weather)
        health = findViewById(R.id.cb_health)
        sports = findViewById(R.id.cb_sports)
        leisure = findViewById(R.id.cb_leisure)
        celebrity = findViewById(R.id.cb_celebrity)
        finance = findViewById(R.id.cb_finance)
        accident = findViewById(R.id.cb_accident)
    }

    private fun getSelectedCheckBoxes(): List<String> {
        val selectedCheckBoxes = mutableListOf<String>()

        if (friend.isChecked) selectedCheckBoxes.add(friend.text.toString())
        if (romantic_relationship.isChecked) selectedCheckBoxes.add(romantic_relationship.text.toString())
        if (family.isChecked) selectedCheckBoxes.add(family.text.toString())
        if (pets.isChecked) selectedCheckBoxes.add(pets.text.toString())
        if (work.isChecked) selectedCheckBoxes.add(work.text.toString())
        if (study.isChecked) selectedCheckBoxes.add(study.text.toString())
        if (chores.isChecked) selectedCheckBoxes.add(chores.text.toString())
        if (social_media.isChecked) selectedCheckBoxes.add(social_media.text.toString())
        if (strangers.isChecked) selectedCheckBoxes.add(strangers.text.toString())
        if (crime.isChecked) selectedCheckBoxes.add(crime.text.toString())
        if (news.isChecked) selectedCheckBoxes.add(news.text.toString())
        if (politics.isChecked) selectedCheckBoxes.add(politics.text.toString())
        if (health.isChecked) selectedCheckBoxes.add(health.text.toString())
        if (weather.isChecked) selectedCheckBoxes.add(weather.text.toString())
        if (sports.isChecked) selectedCheckBoxes.add(sports.text.toString())
        if (leisure.isChecked) selectedCheckBoxes.add(leisure.text.toString())
        if (celebrity.isChecked) selectedCheckBoxes.add(celebrity.text.toString())
        if (finance.isChecked) selectedCheckBoxes.add(finance.text.toString())
        if (accident.isChecked) selectedCheckBoxes.add(accident.text.toString())

        return selectedCheckBoxes
    }
}