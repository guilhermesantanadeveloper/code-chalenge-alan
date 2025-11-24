package com.guilherme.santana.test

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {

    private val userNameEdt = findViewById<EditText>(R.id.userName)
    private val passwordEdt = findViewById<EditText>(R.id.userPassword)
    private val btnLogin = findViewById<Button>(R.id.btnLogin)

    private val viewModel = LoginViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        doLogin()
    }

    fun doLogin() {
        val userName = userNameEdt.text.toString().trim()
        val password = passwordEdt.text.toString().trim()

        btnLogin.setOnClickListener {

            if (viewModel.checkCredentialsAreNotEmpty(userName, password)) {
                toast("Credentials are empty. Fill all the fields")
                return@setOnClickListener
            }

            if (!viewModel.validatePasswordSize(password)) {
                toast("Your password need to have at last 4 characters")
                return@setOnClickListener
            }

            viewModel.checkCredentials(userName, password) { result ->
                if (result) {
                    val intent = Intent(this, WelcomeActivity::class.java)
                    intent.putExtra("username", userName)
                    startActivity(intent)
                } else {
                    toast("Invalid Credentials")
                }
            }

        }
    }
}

fun Context.toast(messsage: String) {
    Toast.makeText(this, messsage, Toast.LENGTH_SHORT).show()
}