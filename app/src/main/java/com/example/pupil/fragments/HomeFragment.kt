package com.example.pupil.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pupil.UserProfileActivity
import com.example.pupil.R
import com.example.pupil.PetProfileActivity
import com.example.pupil.adapters.ReportsListAdapter
import com.example.pupil.model.Report
import com.example.pupil.model.User
import com.example.pupil.model.utilities.EXTRA_REPORT
import com.example.pupil.model.utilities.PetType
import com.example.pupil.services.DataService
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*

class HomeFragment : Fragment() {

    // by default all animals are selected
    var petTypeFilter: EnumSet<PetType> = EnumSet.allOf(PetType::class.java)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onStart() {
        super.onStart()

        val user : User = DataService.getCurrentUser()

        Picasso.get().load(user.photoUrlPath).fit().centerCrop().into(profileImage)
        updateFilterItems()
        prepareReportsList()

        profileItem.setOnClickListener {
            switchToProfile()
        }

        dogTypeFilterView.setOnClickListener {
            togglePetTypeFilter(PetType.DOG)
            prepareReportsList()
        }

        catTypeFilterView.setOnClickListener {
            togglePetTypeFilter(PetType.CAT)
            prepareReportsList()
        }

        otherTypeFilterView.setOnClickListener {
            togglePetTypeFilter(PetType.OTHER)
            prepareReportsList()
        }
    }

    fun prepareReportsList()
    {
        var layoutManager =  LinearLayoutManager(context)
        petReportsRecyclerView.layoutManager = layoutManager

        val reportsList = DataService.getPetReports(petTypeFilter)
        val reportItemClickAction = { report: Report -> switchToReportInfo(report) }
        val adapter =  ReportsListAdapter(reportsList, reportItemClickAction )
        petReportsRecyclerView.adapter = adapter
    }

    fun togglePetTypeFilter(petType: PetType)
    {
        if(petTypeFilter.contains(petType))
        {
            petTypeFilter.remove(petType)
        }
        else
        {
            petTypeFilter.add(petType)
        }
        updateFilterItems()
    }

    fun updateFilterItems()
    {
        val chooseBackground = { item: LinearLayout, petType: PetType ->
            if(petTypeFilter.contains(petType))
                item.setBackgroundResource(R.drawable.pet_filter_frame_selected)
            else
                item.setBackgroundResource(R.drawable.pet_filter_frame)
        }

        chooseBackground(dogTypeFilterView, PetType.DOG)
        chooseBackground(catTypeFilterView, PetType.CAT)
        chooseBackground(otherTypeFilterView, PetType.OTHER)
    }

    fun switchToProfile()
    {
        val intent = Intent(activity, UserProfileActivity::class.java)
        startActivity(intent)
    }

    fun switchToReportInfo(report: Report)
    {
        val intent = Intent(activity, PetProfileActivity::class.java)
        intent.putExtra(EXTRA_REPORT, report)
        startActivity(intent)
    }
}
