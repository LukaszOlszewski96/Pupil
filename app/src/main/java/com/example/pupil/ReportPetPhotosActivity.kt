package com.example.pupil

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_report_pet_photos.*
import kotlinx.android.synthetic.main.activity_report_pet_type.*
import kotlinx.android.synthetic.main.activity_report_pet_type.backArrow

class ReportPetPhotosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_pet_photos)

        //Back screen button
        backArrow.setOnClickListener{backBtn()}

        //Click add photo button
        addPhoto_btn.setOnClickListener {
            //check runtime premission
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){
                    //premission denied
                    val premissions = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE);
                    //show popup to request runtime premission
                    requestPermissions(premissions, PREMISSION_CODE);

                }else{
                    //premission already granted
                    pickImageFromGallery();
                }
            }else{
                //system OS is < Marshmall
                pickImageFromGallery();
            }
        }
    }

    private fun pickImageFromGallery() {
        //Intent to pick image
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }


    //Function to back screen
    private fun backBtn(){
        onBackPressed()
    }

    companion object{



    private val IMAGE_PICK_CODE = 1000;

    private  val PREMISSION_CODE = 1001;
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            PREMISSION_CODE ->{
                if (grantResults.size >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //premission from popup granted
                    pickImageFromGallery()
                }else{
                    //premission from popup denied
                    Toast.makeText(this,"Premission denied", Toast.LENGTH_SHORT).show()

                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            imageView_pet.setImageURI(data?.data)
        }
    }
}


