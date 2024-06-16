package com.group49.mindfulmeadow_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.group49.mindfulmeadow_app.Logging_Process.Logging_fstActivity

class After_SaveActivity : AppCompatActivity() {

    private lateinit var tvUserId: TextView
    private lateinit var tvFeeling: TextView
    private lateinit var tvDescription: TextView
    private lateinit var tvLog: TextView
    private lateinit var tvDate: TextView
    private lateinit var tvAdvice: TextView

    private lateinit var ivBackToHome: ImageView
    private lateinit var btnBackToHome: Button

    private lateinit var homeLayout: RelativeLayout
    private lateinit var moodBackgroundManager: MoodBackgroundManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_after_save)

        tvUserId = findViewById(R.id.tv_userId)
        tvFeeling = findViewById(R.id.tv_feeling)
        tvDescription = findViewById(R.id.tv_description)
        tvLog = findViewById(R.id.tv_log)
        tvDate = findViewById(R.id.tv_date)
        tvAdvice = findViewById(R.id.tv_advice)
        btnBackToHome = findViewById(R.id.btn_back_to_home)
        ivBackToHome = findViewById(R.id.iv_back_to_home)

        homeLayout = findViewById(R.id.after_save_layout)

        moodBackgroundManager = MoodBackgroundManager(this, homeLayout)

        val moodRecord = intent.getSerializableExtra("moodRecord") as? MoodRecord

        // Set the TextViews with MoodRecord data
        moodRecord?.let {
            moodBackgroundManager.updateBackgroundBasedOnMoodRecords(it.userId)
            tvUserId.text = it.userId
            tvFeeling.text = it.feeling
            tvDescription.text = it.description.joinToString(", ")
            tvLog.text = it.log
            tvDate.text = it.date

            val advice = when (it.feeling.split(":").first().trim()) {
                "Genius" -> "Keep shining!"
                "Joy" -> "Well done!"
                "Disgust" -> "It's okay to feel discomfort; it's a sign of your values and standards."
                "Anger" -> "Anger highlights your values and standards, but it can also cloud your judgements. Remember to take deep breaths and let calm guide your actions.\n" +
                        "How about some sweet treats? Sweet food stimulates the release of dopamine... anyway, what's wrong with treating yourself with sweets?"
                "Sad" -> "It's okay to feel sad, just remember that brighter days are ahead. How about some spicy food? Spicy food stimulates the release of endorphins, alleviating your sadness."
                "Fear" -> "Fear can be overwhelming, but it also signals courage. Face it step by step, and you'll find the strength to overcome it. Perhaps something sour? Sour food stimulates your nerves, alleviating stress."
                else -> "No specific advice available."
            }

            tvAdvice.text = advice
        }

        btnBackToHome.setOnClickListener {
            val intent = Intent(this@After_SaveActivity, HomeActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim )
        }

        ivBackToHome.setOnClickListener {
            val intent = Intent(this@After_SaveActivity, HomeActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim )
        }

    }
}