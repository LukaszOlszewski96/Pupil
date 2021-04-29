package com.example.pupil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.pupil.model.Report
import com.example.pupil.model.utilities.EXTRA_REPORT

class ReportPetTypeActivity : AppCompatActivity() {
    lateinit var report : Report
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_pet_type)

        report = intent.getParcelableExtra(EXTRA_REPORT)!!

        Log.i("DEBUGOWANIE", report.type.name)
    }
}