package com.example.pupil

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.example.pupil.adapters.ImageSliderAdapter
import com.example.pupil.fragments.MapSmall
import com.example.pupil.model.Report
import com.example.pupil.model.User
import com.example.pupil.model.utilities.EXTRA_REPORT
import com.example.pupil.model.utilities.EXTRA_REPORTER_INFO
import com.example.pupil.model.utilities.PetGender
import com.example.pupil.model.utilities.ReportType
import com.example.pupil.services.DataService
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_pet_profile.*




class ImageSlider
{
    constructor(context: Context, viewPager : ViewPager, images: ArrayList<String>, onChange: (Int, Int) -> Unit )
    {
        val mSliderAdapter = ImageSliderAdapter(context, images)
        viewPager.adapter = mSliderAdapter
        onChange(0, images.size)
        viewPager.addOnPageChangeListener(object: OnPageChangeListener
        {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int,
            ) { }

            override fun onPageSelected(position: Int) {
                onChange(position, images.size)
            }

            override fun onPageScrollStateChanged(state: Int) {
            }
        })
    }
}

class PetProfileActivity : AppCompatActivity() {

    val mapFragment = MapSmall()

    lateinit var report: Report
    lateinit var reporter: User
    lateinit var imageSlider: ImageSlider
    val currentLocation: Location = Location("User device")
    val reportLocation: Location = Location("Report location")
    lateinit var fuseLocationProvider: FusedLocationProviderClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_profile)

        fuseLocationProvider = LocationServices.getFusedLocationProviderClient(this)
        makeMap()
    }

    override fun onStart() {
        super.onStart()

        backArrow.setOnClickListener { transitionTo_PreviousActivity() }
        contactReporterButton.setOnClickListener { transitionTo_ReporterActivity() }
        report = intent.getParcelableExtra(EXTRA_REPORT)
        val reporterDataReceived: (User) -> Unit = { user -> onReporterInfoReceived(user) }
        DataService.getUserById(report.reporterId, reporterDataReceived)

        imageSlider = ImageSlider(this, imagesViewPager, report.pet.photos, {pos, size -> onPetImageChange(pos,size)})

        pet_name.text = report.pet.name
        pet_breed.text = report.pet.breed
        pet_age.text = report.pet.age
        pet_color.text = report.pet.color
        when(report.pet.gender)
        {
            PetGender.MALE -> pet_gender_icon.setImageResource(R.drawable.male)
            else -> pet_gender_icon.setImageResource(R.drawable.female)
        }
        when(report.type)
        {
            ReportType.MISSED_PET -> report_type_image.setImageResource(R.drawable.paw_red)
            else -> report_type_image.setImageResource(R.drawable.paw_green)
        }
        special_marks.text = report.pet.specialMarks
        about_pet.text = report.pet.description
        report_date.text = report.missingDate
        reportLocation.longitude = report.longitude
        reportLocation.latitude = report.latitude
        mapFragment.addLocationMark(report.longitude, report.latitude, true)
        currentLocation.longitude = 51.05553
        currentLocation.latitude = 17.01591

        requestLocationPermission()
    }

    companion object
    {
        private const val LOCATION_PERMISSION_CODE = 3000
    }

    fun requestLocationPermission()
    {
        // if not older than Marshmallow
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)
            {
                readLocation()
            }
            else
            {
                val permission = arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION)
                requestPermissions(permission, LOCATION_PERMISSION_CODE)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    )
    {
        when(requestCode){
            LOCATION_PERMISSION_CODE ->
            {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    //permission from popup granted
                    readLocation()
                }
                else
                {
                    //permission from popup denied
                    Toast.makeText(this, "Access to location denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun readLocation()
    {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            fuseLocationProvider.lastLocation.addOnSuccessListener(this, { location ->
                currentLocation.longitude = location.longitude
                currentLocation.latitude = location.latitude
                updateDistance()
            })
        }
    }

    fun updateDistance()
    {
        val distanceToReportLocation = currentLocation.distanceTo(reportLocation)/1000.0f
        distance.text = "%.2f km".format(distanceToReportLocation)
    }

    fun onReporterInfoReceived(user: User)
    {
        reporter = user
        Picasso.get().load(reporter.photoUrlPath).fit().centerCrop().into(profileImage)
        val fullName: String =  "${reporter.name} ${reporter.surname}"
        owner_name.text = fullName
    }

    fun onPetImageChange(pos: Int, size: Int)
    {
        val imageOfImages: String = "${pos+1}/$size"
        number_of_photos.text = imageOfImages
    }

    fun makeMap()
    {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.map_fragment, mapFragment)
        fragmentTransaction.commit()
        transparent_Map.setOnTouchListener { view, event -> onMapTouch(view, event) }
    }

    fun onMapTouch(view: View, event: MotionEvent): Boolean
    {
        view.performClick()
        var result: Boolean = true
        when(event.action)
        {
            MotionEvent.ACTION_DOWN -> {
                scrollView.requestDisallowInterceptTouchEvent(true)
                result = false
            }
            MotionEvent.ACTION_UP -> {
                scrollView.requestDisallowInterceptTouchEvent(false)
                result = true
            }

            MotionEvent.ACTION_MOVE -> {
                scrollView.requestDisallowInterceptTouchEvent(true)
                result = false
            }
            else ->  result = true
        }
        return result
    }

    fun  transitionTo_PreviousActivity()
    {
        onBackPressed()
    }

    fun transitionTo_ReporterActivity()
    {
        val intent = Intent(this, ReporterContactActivity::class.java)
        intent.putExtra(EXTRA_REPORTER_INFO, reporter)
        startActivity(intent)
    }

}