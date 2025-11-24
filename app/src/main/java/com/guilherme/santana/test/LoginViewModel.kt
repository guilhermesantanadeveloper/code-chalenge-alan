package com.guilherme.santana.test

import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {


    fun checkCredentialsAreNotEmpty(userName: String, password: String): Boolean {
        return userName.isNotEmpty() && !password.isNotEmpty()
    }

    fun validatePasswordSize(password: String): Boolean {
        return password.length >= 5
    }

    fun checkCredentials(
        userName: String,
        password: String,
        onResult: (Boolean) -> Unit
    ) {
        val user = "Guilherme"
        val pass = "1234"

        if (user == userName && password == pass) {
            onResult.invoke(true)
        } else {
            onResult.invoke(false)
        }
    }
}