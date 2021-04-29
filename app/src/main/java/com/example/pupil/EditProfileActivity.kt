package com.example.pupil

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pupil.model.User
import com.example.pupil.services.DataService
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_edit_profile.*

class EditProfileActivity : AppCompatActivity() {

    var user : User = User()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        user = DataService.getCurrentUser()

        fillUserData()

        backArrow.setOnClickListener {
            transitionTo_PreviousActivity()
        }

        saveButton.setOnClickListener {
            saveNewUserData()
            transitionTo_PreviousActivity()
        }

        changePictureItem.setOnClickListener {
            chooseNewImage()
        }
    }

    companion object
    {
        private const val EXTERNAL_STORAGE_PERMISSION_CODE = 1000
        private const val IMAGE_PICK_CODE = 2000
    }

    fun fillUserData()
    {
        Picasso.get().load(user.photoUrlPath).fit().centerCrop().into(profileImage)
        userNameEditText.setText(user.name)
        userSurnameEditText.setText(user.surname)
        userCityEditText.setText(user.city)
        userPhoneEditText.setText(user.phoneNumber)
        userEmailEditText.setText(user.email)
    }

    fun chooseNewImage()
    {
        // if not older than Marshmallow
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            // if permission granted
            if(checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            {
                pickNewImageFromGallery()
            }
            else
            {
                val permission = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE);
                requestPermissions(permission, EXTERNAL_STORAGE_PERMISSION_CODE)
            }
        }
        // older than Marshmallow
        else
        {
            pickNewImageFromGallery()
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    )
    {
        when(requestCode){
            EXTERNAL_STORAGE_PERMISSION_CODE ->
            {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    //permission from popup granted
                    pickNewImageFromGallery()
                }
                else
                {
                    //permission from popup denied
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun pickNewImageFromGallery()
    {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE)
        {
            val imageUri: Uri? = data?.data
            user.photoUrlPath = imageUri.toString()
            profileImage.setImageURI(imageUri)
        }
    }

    fun saveNewUserData()
    {
        user.name = userNameEditText.text.toString()
        user.surname = userSurnameEditText.text.toString()
        user.city = userCityEditText.text.toString()
        user.phoneNumber = userPhoneEditText.text.toString()
        user.email = userEmailEditText.text.toString()

        DataService.updateUserInfo(user)
    }

    fun transitionTo_PreviousActivity()
    {
        onBackPressed()
    }
}