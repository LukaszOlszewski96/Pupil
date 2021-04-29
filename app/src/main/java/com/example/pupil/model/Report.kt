package com.example.pupil.model

import android.os.Parcel
import android.os.Parcelable
import com.example.pupil.model.utilities.ReportType

class Report (reportType: ReportType, pet: Pet): Parcelable {
    var id: Int = 0
    var reporterId = 0
    var type: ReportType = reportType
    var pet: Pet = pet
    var missingDate: String = String()
    var longitude : Double = 0.0
    var latitude: Double = 0.0

    constructor(parcel: Parcel) : this(ReportType.MISSED_PET, Pet()) {
        id = parcel.readInt()
        reporterId = parcel.readInt()
        type = parcel.readSerializable() as ReportType
        pet = parcel.readParcelable<Pet>(Pet::class.java.classLoader) as Pet
        missingDate = parcel.readString() as String
        longitude = parcel.readDouble()
        latitude = parcel.readDouble()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(reporterId)
        parcel.writeSerializable(type)
        parcel.writeParcelable(pet, flags)
        parcel.writeString(missingDate)
        parcel.writeDouble(longitude)
        parcel.writeDouble(latitude)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Report> {
        override fun createFromParcel(parcel: Parcel): Report {
            return Report(parcel)
        }

        override fun newArray(size: Int): Array<Report?> {
            return arrayOfNulls(size)
        }
    }
}