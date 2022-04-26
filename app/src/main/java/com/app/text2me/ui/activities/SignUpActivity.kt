package com.app.text2me.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.app.text2me.R
import com.app.text2me.databinding.ActivitySignUpBinding
import com.tapadoo.alerter.Alerter

class SignUpActivity : AppCompatActivity() {
    private var binding: ActivitySignUpBinding? = null
    private var agreedTerms = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        handleEvents()
    }

    private fun handleEvents() {
        binding?.ivAgree?.setOnClickListener {
            if (agreedTerms) {
                agreedTerms = false
                binding?.ivAgree?.setImageResource(R.drawable.ic_no_tick)
                binding?.tvBtnReg?.alpha = 0.2f

            } else {
                agreedTerms = true
                binding?.ivAgree?.setImageResource(R.drawable.ic_tick)
                binding?.tvBtnReg?.alpha = 1f
            }
        }
        binding?.tvBtnReg?.setOnClickListener {
            if (agreedTerms) {
                val phone = binding?.etTenantId?.text.toString()
                val password = binding?.etPassword?.text.toString()
                val confirmPassword = binding?.etConfirmPassword?.text.toString()
                if (validation(phone, password, confirmPassword)) {
                    val i = Intent(this@SignUpActivity, MainActivity::class.java)
                    startActivity(i)
                    finish()

                }
            } else {
                Toast.makeText(
                    this@SignUpActivity,
                    "please accept terms and conditions",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun validation(phone: String, password: String, Cpassword: String): Boolean {
        when {
            phone.isEmpty() -> {
                showAlert("Register", "please enter Email", "ID")
                return false
            }
            password.isEmpty() -> {
                showAlert("Register", "please enter password", "password must 8 digits")
                return false
            }
            Cpassword != password -> {
                showAlert("Register", "please enter password", "password not matched")
                return false
            }
            else -> return true
        }

    }

    private fun showAlert(title: String, msg: String, clickMsg: String) {
        Alerter.create(this@SignUpActivity)
            .setTitle(title)
            .setText(msg)
            .setDuration(1000)
            .setBackgroundColorRes(R.color.purple_200)
            .setOnClickListener(View.OnClickListener {
                Toast.makeText(
                    this@SignUpActivity,
                    clickMsg,
                    Toast.LENGTH_SHORT
                ).show();
            })
            .show()

    }
}