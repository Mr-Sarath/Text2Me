package com.app.text2me.ui.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.app.text2me.R
import com.app.text2me.databinding.ActivityLoginBinding
import com.tapadoo.alerter.Alerter

class LoginActivity : AppCompatActivity() {
    private var binding:ActivityLoginBinding?=null
    private var preference: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        preference = getSharedPreferences("pref", Context.MODE_PRIVATE)

        init()
    }

    private fun init() {
        binding?.llRegister?.setOnClickListener {
            val i = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(i)
            finish()

        }
        binding?.tvBtnLogin?.setOnClickListener {
            val phone = binding?.etTenantId?.text.toString()
            val password = binding?.etPassword?.text.toString()
            if (validation(phone, password)) {
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)            }
        }

    }

    private fun validation(phone: String, password: String): Boolean {
        if (phone.isEmpty()) {
            showAlert("Login", "please enter Your Email", "ID")
            return false
        } else if (password.isEmpty()) {
            showAlert("Login", "please enter password", "Enter your Password")
            return false
        }

        return true
    }

    private fun showAlert(title: String, msg: String, clickMsg: String) {
        Alerter.create(this@LoginActivity)
            .setTitle(title)
            .setText(msg)
            .setDuration(1000)
            .setBackgroundColorRes(R.color.purple_200)
            .setOnClickListener {
                Toast.makeText(
                    this@LoginActivity,
                    clickMsg,
                    Toast.LENGTH_SHORT
                ).show();
            }
            .show()

    }
}