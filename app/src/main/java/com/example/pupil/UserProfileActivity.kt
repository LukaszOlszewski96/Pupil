package com.example.pupil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import com.example.pupil.model.User
import com.example.pupil.services.DataService
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_user_profile.*

class UserProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.e("KAMIL", "UserProfileActivity::onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        backArrow.setOnClickListener { transitionTo_MenuActivity() }
        editProfile.setOnClickListener { transitionTo_EditProfileActivity() }
        changePassword.setOnClickListener { transitionTo_ChangePasswordActivity() }
        aboutUs.setOnClickListener { transitionTo_AboutUsActivity() }
        logout.setOnClickListener { transitionTo_LogoutActivity() }
    }

    override fun onStart() {
        super.onStart()
        val user : User = DataService.getCurrentUser()

        Picasso.get().load(user.photoUrlPath).fit().centerCrop().into(profileImage)
        updateUserNameTextView("${user.name} ${user.surname}")
    }

    fun updateUserNameTextView(userName: String)
    {
        userNameTextView.text = userName
    }

    fun transitionTo_MenuActivity()
    {
        onBackPressed()
    }

    fun transitionTo_EditProfileActivity()
    {
        val intent = Intent(this, EditProfileActivity::class.java)
        startActivity(intent)
    }

    fun transitionTo_ChangePasswordActivity()
    {
        val intent = Intent(this, ChangePasswordActivity::class.java)
        startActivity(intent)
    }

    fun transitionTo_AboutUsActivity()
    {
        val intent = Intent(this, AboutUsActivity::class.java)
        startActivity(intent)
    }

    fun transitionTo_LogoutActivity()
    {
        val intent = Intent(this, LogoutActivity::class.java)
        startActivity(intent)
    }

}