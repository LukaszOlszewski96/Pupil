package com.example.pupil.model

import android.os.Parcel
import android.os.Parcelable
import com.example.pupil.model.utilities.PetGender
import com.example.pupil.model.utilities.PetType
import java.util.*

class Pet() : Parcelable{
    var petType: PetType = PetType.OTHER
    var gender: PetGender = PetGender.MALE
    var name: String = ""
    var breed: String = ""
    var age: String = ""
    var color: String = ""
    var specialMarks: String = ""
    var description: String = ""
    var photos = ArrayList<String>()

    constructor(parcel: Parcel) : this() {
        petType = parcel.readSerializable() as PetType
        gender = parcel.readSerializable() as PetGender
        name = parcel.readString().toString()
        breed = parcel.readString().toString()
        age = parcel.readString().toString()
        color = parcel.readString().toString()
        specialMarks = parcel.readString().toString()
        description = parcel.readString().toString()
        photos = parcel.readSerializable() as ArrayList<String>

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeSerializable(petType)
        parcel.writeSerializable(gender)
        parcel.writeString(name)
        parcel.writeString(breed)
        parcel.writeString(age)
        parcel.writeString(color)
        parcel.writeString(specialMarks)
        parcel.writeString(description)
        parcel.writeSerializable(photos)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Pet> {
        override fun createFromParcel(parcel: Parcel): Pet {
            return Pet(parcel)
        }

        override fun newArray(size: Int): Array<Pet?> {
            return arrayOfNulls(size)
        }
    }
}