package com.example.pupil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_report_pet_type.*

class ReportPetGeneralInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_pet_general_info)

        //Next screen button
        nextReportBtn.setOnClickListener {nextBtn()}
        //Back screen button
        backArrow.setOnClickListener{backBtn()}
    }

    //Function to back screen
    private fun backBtn(){
        onBackPressed()
    }

    //Function to next screen
    private fun nextBtn(){
        startActivity(Intent(this, ReportPetDescriptionActivity :: class.java))
    }
}