package com.group49.mindfulmeadow_app

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.group49.mindfulmeadow_app.Logging_Process.Logging_fourthActivity

class LogAndRegisterActivity : AppCompatActivity() {

    private lateinit var et_username: EditText
    private lateinit var et_password: EditText
    private lateinit var tv_log_in: TextView
    private lateinit var tv_register: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_and_register)

        et_username = findViewById(R.id.et_username)
        et_password = findViewById(R.id.et_password)
        tv_register = findViewById(R.id.tv_register)
        tv_log_in = findViewById(R.id.tv_log_in)

        tv_log_in.setOnClickListener {
            DataBase.verifyUserCredentials(et_username.text.toString(), et_password.text.toString(), {
                a -> OnLoginResultReturn(a, et_username.text.toString())
            })
        }

        tv_register.setOnClickListener{
            DataBase.registerUser(et_username.text.toString(), et_password.text.toString(), resultCallBack = {
                a -> OnRegisterResultReturn(a, et_username.text.toString())
            })
        }
    }

    private fun OnLoginResultReturn(succ :Boolean, username: String){
        if(succ){
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Successfully Logged in!")
            val preferences = PreferenceManager.getDefaultSharedPreferences(this)
            preferences.edit().putString("username", username).apply()
            builder.setPositiveButton("OK") { dialog, _ ->
                val intent = Intent(this@LogAndRegisterActivity, HomeActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim )
            }
            builder.show()
        } else {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Wrong credentials, please try again")
            builder.setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            builder.show()
        }
    }

    private fun OnRegisterResultReturn(succ :Boolean, username: String){
        if(succ){
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Successfully Registered and Logged in!")
            val preferences = PreferenceManager.getDefaultSharedPreferences(this)
            preferences.edit().putString("username", username).apply()
            builder.setPositiveButton("OK") { dialog, _ ->
                val intent = Intent(this@LogAndRegisterActivity, HomeActivity::class.java)
                startActivity(intent)
            }
            builder.show()
        } else {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Register Failed, please try again")
            builder.setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            builder.show()
        }
    }
}