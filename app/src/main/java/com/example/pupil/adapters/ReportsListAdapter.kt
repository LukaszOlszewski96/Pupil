package com.example.pupil.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pupil.R
import com.example.pupil.model.Report
import com.example.pupil.model.utilities.ReportType
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.report_layout.view.*

class ViewHolder(val itemView: View, val itemClick: (Report) -> Unit) : RecyclerView.ViewHolder(itemView)
{
    val name: TextView = itemView.pet_name
    val breed: TextView = itemView.pet_breed
    val image: ImageView = itemView.pet_image
    val reportTypeImage: ImageView = itemView.report_type_image

    fun bindReport(petReport: Report)
    {
        Picasso.get().load(petReport.pet.photos[0]).fit().centerCrop().into(image)
        when(petReport.type)
        {
            ReportType.MISSED_PET -> reportTypeImage.setImageResource(R.drawable.paw_red)
            else -> reportTypeImage.setImageResource(R.drawable.paw_green)
        }
        name.text = petReport.pet.name
        breed.text = petReport.pet.breed

        itemView.setOnClickListener { itemClick(petReport) }
    }
}

class ReportsListAdapter(val reports: List<Report>, val itemClick: (Report) -> Unit): RecyclerView.Adapter<ViewHolder>()
{
    override fun getItemCount() = reports.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val inflater = LayoutInflater.from(parent.context)
        val itemView: View = inflater.inflate(R.layout.report_layout , parent, false)
        return ViewHolder(itemView, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        val petReport: Report = reports[position]
        holder.bindReport(petReport)
    }
}