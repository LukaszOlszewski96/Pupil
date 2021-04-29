package com.example.pupil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    fun onClickGetStartedBtn(view: View)
    {
        val loginActivityIntent = Intent(this, Login::class.java)
        startActivity(loginActivityIntent)
    }

}