package com.example.pupil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import com.example.pupil.model.Report
import com.example.pupil.model.utilities.EXTRA_REPORT
import com.example.pupil.model.utilities.PetType
import kotlinx.android.synthetic.main.activity_report_pet_type.*
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*

class ReportPetTypeActivity : AppCompatActivity() {
    lateinit var report : Report
    // by default all animals are selected
    var petTypeFilter: EnumSet<PetType> = EnumSet.allOf(PetType::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_pet_type)

        report = intent.getParcelableExtra(EXTRA_REPORT)!!

        Log.i("DEBUGOWANIE", report.type.name)

        nextReportBtn.setOnClickListener {nextBtn()}
        backArrow.setOnClickListener{backBtn()}
    }

    override fun onStart() {
        super.onStart()

        //Switch pet type:
        dogTypeView.setOnClickListener {
            togglePetTypeFilter(PetType.DOG)
        }

        catTypeView.setOnClickListener {
            togglePetTypeFilter(PetType.CAT)
        }

        otherTypeView.setOnClickListener {
            togglePetTypeFilter(PetType.OTHER)
        }
    }


    //Switch pet type, set background color after click:
    private fun togglePetTypeFilter(petType: PetType)
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

    //Switch pet type:
    private fun updateFilterItems()
    {
        val chooseBackground = { item: LinearLayout, petType: PetType ->
            if(petTypeFilter.contains(petType))
                item.setBackgroundResource(R.drawable.pet_filter_frame_selected)
            else
                item.setBackgroundResource(R.drawable.pet_filter_frame)
        }

        chooseBackground(dogTypeView, PetType.DOG)
        chooseBackground(catTypeView, PetType.CAT)
        chooseBackground(otherTypeView, PetType.OTHER)
    }

   private fun backBtn(){
        onBackPressed()
    }

    private fun nextBtn(){
            startActivity(Intent(this, ReportPetGeneralInfoActivity :: class.java))
        }
}