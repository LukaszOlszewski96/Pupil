package com.example.pupil.model

import android.os.Parcel
import android.os.Parcelable

class User() : Parcelable {
    var id : Int = 0
    var name: String = "Name"
    var surname: String = "Surname"
    var city: String = "City"
    var phoneNumber : String = "+00 000000000"
    var email: String = "email.address@poczta.eu"
    var photoUrlPath: String = ""

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        name = parcel.readString().toString()
        surname = parcel.readString().toString()
        city = parcel.readString().toString()
        phoneNumber = parcel.readString().toString()
        email = parcel.readString().toString()
        photoUrlPath = parcel.readString().toString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(surname)
        parcel.writeString(city)
        parcel.writeString(phoneNumber)
        parcel.writeString(email)
        parcel.writeString(photoUrlPath)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}
