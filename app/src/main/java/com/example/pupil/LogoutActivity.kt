package com.example.pupil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.pupil.services.DataService
import kotlinx.android.synthetic.main.activity_logout.*

class LogoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logout)

        logoutButton.setOnClickListener { logoutUser() }
        backArrow.setOnClickListener { transitionTo_PreviousActivity() }
    }

    fun transitionTo_PreviousActivity()
    {
        onBackPressed()
    }

    fun logoutUser()
    {
        val onLogoutSuccess : () -> Unit = {
            transitionTo_LoginActivity()
        }

        val onLogoutFailure: () -> Unit = {
            toast("Logout failed!")
        }

        DataService.logoutUser(onLogoutSuccess, onLogoutFailure)
    }

    fun transitionTo_LoginActivity()
    {
        val intent = Intent(this, Login::class.java )
        startActivity(intent)
    }
    fun toast(text: String)
    {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}