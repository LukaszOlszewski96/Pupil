package com.example.pupil.services

import com.example.pupil.R
import com.example.pupil.model.Pet
import com.example.pupil.model.Report
import com.example.pupil.model.User
import com.example.pupil.model.utilities.PetGender
import com.example.pupil.model.utilities.PetType
import com.example.pupil.model.utilities.ReportType
import java.util.*
import kotlin.collections.ArrayList

object DummyDataProvider
{
    var users : ArrayList<User> = arrayListOf (
        makeUser(1, "Ives", "Doe", "Wroclaw", "111222333", "i.d@wwsis.eu", "https://images.pexels.com/photos/773371/pexels-photo-773371.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940" ),
        makeUser(2, "Jannie", "Moira", "Lubin", "222333111", "j.moira@wwsis.eu", "https://images.pexels.com/photos/428321/pexels-photo-428321.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940" ),
        makeUser(3, "Anna", "Nowak", "Leśnica", "333111222", "anna.n@wwsis.eu", "https://images.pexels.com/photos/1130626/pexels-photo-1130626.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940" ),
        makeUser(4, "John", "Border", "Warsaw", "444555666", "j.b@wwsis.eu", "https://images.pexels.com/photos/1933873/pexels-photo-1933873.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940" ),
        makeUser(5, "Adam", "Pierwszy", "Wroclaw", "555666444", "adas@wwsis.eu", "https://images.pexels.com/photos/1080213/pexels-photo-1080213.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940" ),
        makeUser(6, "Roberto Rich", "Martin", "LosAngeles", "666444555", "rr.martin@wwsis.eu", "https://images.pexels.com/photos/1043474/pexels-photo-1043474.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940" )
    )

    var pets: ArrayList<Pet> = arrayListOf(
        makePet("Maddy", "Pug", PetType.DOG, PetGender.FEMALE, "3.2", "Black",  "It is a pug", "Small black with big eyes", arrayListOf(
            "https://images.pexels.com/photos/1851164/pexels-photo-1851164.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
            "https://images.pexels.com/photos/1851188/pexels-photo-1851188.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
            "https://images.pexels.com/photos/2437628/pexels-photo-2437628.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940"
            ) ),
        makePet("Norman", "Mixed", PetType.DOG, PetGender.MALE, "1.5", "Ginger",  "Smart gignger dog", "Nothing special", arrayListOf(
            "https://images.pexels.com/photos/546228/pexels-photo-546228.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
            "https://images.pexels.com/photos/546229/pexels-photo-546229.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940"
            ) ),
        makePet("Brenda", "Burmilla", PetType.CAT, PetGender.FEMALE, "2.1", "White", "I lost him during Valentine's Day", "Small with marks on the tail", arrayListOf(
            "https://kocizawrotglowy.files.wordpress.com/2016/04/koty-pozostale-rasy_big_10_1196996005.jpg",
            "https://d17fnq9dkz9hgj.cloudfront.net/breed-uploads/2018/08/burmilla-card-large.jpg?bust=1535569890&width=600"
        ) ),
        makePet("Babi", "", PetType.OTHER, PetGender.MALE, "0.5", "Brown",  "Lost at the beginning of the year. ", "Blue eyes, mixed color (white and brown)", arrayListOf(
            "https://images.pexels.com/photos/372166/pexels-photo-372166.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940"
        ) )
    )

    var reports : ArrayList<Report> = arrayListOf(
        makeReport(1, 6, ReportType.MISSED_PET, pets[0],"2021-01-16", 17.02,  51.08 ),
        makeReport(2, 5, ReportType.FOUND_PET, pets[1], "2020-12-31", 16.871432, 51.146053 ),
        makeReport(3, 4, ReportType.FOUND_PET, pets[2], "2019-02-14", 17.296909,  50.947480 ),
        makeReport(4, 3, ReportType.MISSED_PET, pets[3], "2021-01-02", 22.994967, 54.100581 )
    )

    fun makeUser(id: Int, name: String, surname: String, city: String, phone: String, email: String, photo: String): User
    {
        var user: User = User()
        user.id = id
        user.name = name
        user.surname = surname
        user.city = city
        user.phoneNumber = phone
        user.email = email
        user.photoUrlPath = photo
        return user
    }

    fun makeReport(id: Int, reporterId: Int, type: ReportType, pet: Pet, date: String, longitude: Double, latitude: Double): Report
    {
        var report: Report = Report(type, pet)
        report.id = id
        report.reporterId = reporterId
        report.missingDate = date
        report.longitude = longitude
        report.latitude = latitude

        return report
    }

    fun makePet(name: String, breed: String, type: PetType, gender: PetGender, age: String, color: String, description: String, specials: String, photos: ArrayList<String>): Pet
    {
        var pet: Pet = Pet()

        pet.petType = type
        pet.gender = gender
        pet.name = name
        pet.breed = breed
        pet.age = age
        pet.color = color
        pet.specialMarks = specials
        pet.description = description
        pet.photos = photos

        return pet
    }

}

object DataService {

    private var currentUser: User = DummyDataProvider.users[0]
    private var logoutStatus: Boolean = true // Only for tests purposes

    fun getCurrentUser(): User
    {
        // read from database
        return currentUser
    }

    fun logoutUser(onSuccess: () -> Unit, onFailure: () -> Unit )
    {
        // Logout user using Firebase
        // call onSuccess when finished successfully
        // call onFailure when logout failed
        if(logoutStatus)
        {
            onSuccess()
        }
        else
        {
            onFailure()
        }
        // Only for tests purposes just like if statement above
        logoutStatus = !logoutStatus
    }

    fun getUserById(id: Int, onSuccess: (User) -> Unit)
    {
        // Do whatever is needed to get user data from database -> It is needed for pet owner data
        // search by Id
        var foundUser: User = User()

        for(user in DummyDataProvider.users)
        {
            if(user.id == id)
            {
                foundUser = user
                break
            }
        }

        onSuccess(foundUser)
    }

    fun getAboutDescription(onSuccess: (String) -> Unit)
    {
        val aboutText :String = "This application was created by students of WWSIS \"horyzont\" University in Wrocław, Poland.The main concept for this application was to fulfill two main ideas, learn Android applicatin development skills and create an app that could be useful for someone. We decided to make an application that can help peoples that own an animal. \n\n Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum. \n\n Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."

        // get description from database
        // if received call onSuccess
        onSuccess(aboutText)
    }

    fun updateUserInfo(user: User)
    {
        // write to database
        currentUser = user
    }

    fun changeUserPassword(curr: String, new: String, confirm: String, onResult: (String) -> Unit )
    {
        // Change password for current user in database
        // result string and check below is only example is an example
        if(curr == "qwerty")
        {
            onResult("OK")
        }
        else
        {
            onResult("FAILURE")
        }
    }

    fun getPetReports(petTypeFilter: EnumSet<PetType>): List<Report>
    {
        val reports =  DummyDataProvider.reports
        val filteredList = reports.filter{ petTypeFilter.contains(it.pet.petType)}

        return filteredList
    }



}