package com.example.pupil.fragments


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import kotlinx.android.synthetic.main.fragment_report.*

import com.example.pupil.R
import com.example.pupil.ReportPetTypeActivity
import com.example.pupil.UserProfileActivity
import com.example.pupil.model.Pet
import com.example.pupil.model.Report
import com.example.pupil.model.User
import com.example.pupil.model.utilities.EXTRA_REPORT
import com.example.pupil.model.utilities.ReportType
import com.example.pupil.services.DataService
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_report.profileImage

class ReportFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_report, container, false)
    }

    override fun onStart() {
        super.onStart()
        val user : User = DataService.getCurrentUser()

        Picasso.get().load(user.photoUrlPath).fit().centerCrop().into(profileImage)

        foundPetBtn.setOnClickListener { switchToReportCreation(ReportType.FOUND_PET) }
        missedPetBtn.setOnClickListener { switchToReportCreation(ReportType.MISSED_PET) }
        profileItem.setOnClickListener { switchToProfile() }
    }

    fun switchToReportCreation( reportType: ReportType)
    {
        val intent = Intent(activity, ReportPetTypeActivity::class.java)
        val report = Report(reportType, Pet())
        intent.putExtra(EXTRA_REPORT, report)
        startActivity(intent)
    }

    fun switchToProfile()
    {
        val intent = Intent(activity, UserProfileActivity::class.java)
        startActivity(intent)
    }
}

