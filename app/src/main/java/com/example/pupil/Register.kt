package com.example.pupil

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //Button for change screen method -> change on Login_activity
        val btnBackSignIn = findViewById<View>(R.id.back_signIn) as Button
        btnBackSignIn.setOnClickListener(View.OnClickListener {
                view -> backToSign()
        })
    }

    //Method: back to Login activity
    private fun backToSign(){
        startActivity(Intent(this, Login :: class.java))
    }
}