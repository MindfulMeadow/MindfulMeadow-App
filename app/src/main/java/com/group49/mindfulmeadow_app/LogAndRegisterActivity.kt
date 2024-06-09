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
            val username = et_username.text.toString()
            val password = et_password.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                showErrorDialog("Username and Password cannot be empty")
            } else {
                DataBase.verifyUserCredentials(username, password) { success ->
                    OnLoginResultReturn(success, username)
                }
            }
        }

        tv_register.setOnClickListener{
            val username = et_username.text.toString()
            val password = et_password.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                showErrorDialog("Username and Password cannot be empty")
            } else {
                DataBase.registerUser(username, password) { success ->
                    OnRegisterResultReturn(success, username)
                }
            }
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
            showErrorDialog("Wrong credentials, please try again")
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
            showErrorDialog("Register Failed, please try again")
        }
    }

    private fun showErrorDialog(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage(message)
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }
}