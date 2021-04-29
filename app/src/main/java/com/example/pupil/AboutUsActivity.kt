package com.example.pupil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout.JUSTIFICATION_MODE_INTER_WORD
import android.text.method.ScrollingMovementMethod
import com.example.pupil.services.DataService
import kotlinx.android.synthetic.main.activity_about_us.*

class AboutUsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)

        backArrow.setOnClickListener { transitionTo_PreviousActivity() }

        val onSuccess : (String) -> Unit = { aboutText -> changeAboutText(aboutText)}
        DataService.getAboutDescription(onSuccess)
    }

    fun transitionTo_PreviousActivity()
    {
        onBackPressed()
    }

    fun changeAboutText(aboutText: String)
    {
        aboutTextView.text = aboutText
        aboutTextView.movementMethod = ScrollingMovementMethod.getInstance();
    }
}
