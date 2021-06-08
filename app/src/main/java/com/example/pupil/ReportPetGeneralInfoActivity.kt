package com.example.pupil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import com.example.pupil.model.utilities.PetGender
import com.example.pupil.model.utilities.PetType
import kotlinx.android.synthetic.main.activity_report_pet_general_info.*
import kotlinx.android.synthetic.main.activity_report_pet_type.*
import kotlinx.android.synthetic.main.activity_report_pet_type.backArrow
import kotlinx.android.synthetic.main.activity_report_pet_type.nextReportBtn
import java.util.*

class ReportPetGeneralInfoActivity : AppCompatActivity() {

    var genderTypeFilter: EnumSet<PetGender> = EnumSet.allOf(PetGender::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_pet_general_info)

        //Next screen button
        nextReportBtn.setOnClickListener {nextBtn()}
        //Back screen button
        backArrow.setOnClickListener{backBtn()}
    }




    //Button Male and Female function:

    override fun onStart() {
        super.onStart()

        //Switch pet gender type:
        genderFemaleTypeView.setOnClickListener{
            toggleGenderType(PetGender.FEMALE)
        }
        genderMaleTypeView.setOnClickListener{
            toggleGenderType(PetGender.MALE)
        }

    }


    private fun toggleGenderType(gender: PetGender){
        if(genderTypeFilter.contains(gender)){
            genderTypeFilter.remove(gender)
        }else{
            genderTypeFilter.add(gender)
        }
        updateFilterItems()
    }

    private fun updateFilterItems(){
        val chooseBackground = { item: LinearLayout, gender: PetGender ->
            if (genderTypeFilter.contains(gender)) {
                item.setBackgroundResource(R.drawable.pet_filter_frame_selected)
            }else{
                    item.setBackgroundResource(R.drawable.pet_filter_frame)
                }
            }
            chooseBackground(genderMaleTypeView, PetGender.MALE)
            chooseBackground(genderFemaleTypeView, PetGender.FEMALE)
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