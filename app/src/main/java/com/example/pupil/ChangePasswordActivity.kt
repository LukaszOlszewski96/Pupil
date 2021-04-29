package com.example.pupil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.pupil.services.DataService
import kotlinx.android.synthetic.main.activity_change_password.*
import kotlinx.android.synthetic.main.activity_change_password.backArrow

class ChangePasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        backArrow.setOnClickListener { transitionTo_PreviousActivity() }
        saveButton.setOnClickListener { changeUserPassword()  }
    }

    fun changeUserPassword()
    {
        val currPass: String = currentPasswordEditText.text.toString()
        val newPass: String = newPasswordEditText.text.toString()
        val confirmPass: String = confirmPasswordEditText.text.toString()

        if (validateData(currPass, newPass, confirmPass))
        {
            val handleResult: (String) -> Unit = { result ->
                // TODO check result
                if(result == "OK")
                {
                    toast("Password changed successfully")
                    // Todo Transition to previous activity
                }
                else
                {
                    toast("Password change failed!")
                }
            }
            DataService.changeUserPassword(currPass, newPass, confirmPass, handleResult)
        }
    }

    fun validateData(curr: String, newPass: String, confirm: String): Boolean
    {
        if(curr.isNullOrEmpty() or newPass.isNullOrEmpty() or confirm.isNullOrEmpty() )
        {
            toast("All fields must be filled")
            return false
        }
        else if(newPass == curr)
        {
            toast("New and current password can not be the same")
            return false
        }
        else if(newPass != confirm)
        {
            toast("New passwords mismatch")
            return false
        }
        else // passwords are entered correctly
        {
            return true
        }
    }

    fun toast(msg: String)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    fun transitionTo_PreviousActivity()
    {
        onBackPressed()
    }
}