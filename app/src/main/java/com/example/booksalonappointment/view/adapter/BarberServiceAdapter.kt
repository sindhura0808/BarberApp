package com.example.booksalonappointment.view.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.booksalonappointment.R
import com.example.booksalonappointment.databinding.SelectBarberServiceItemBinding
import com.example.booksalonappointment.model.remote.Constants.BASE_IMAGE_URL
import com.example.booksalonappointment.model.remote.response.BarberService
import com.example.booksalonappointment.view.BarberServiceActivity
import com.example.booksalonappointment.view.TimeSelectionActivity

class BarberServiceAdapter (
    private val context: Context,
    private val barbers: List<BarberService>
) :
    RecyclerView.Adapter<BarberServiceAdapter.BarberServiceViewHolder>() {

    lateinit var binding: SelectBarberServiceItemBinding
    override fun getItemCount() = barbers.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BarberServiceViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = SelectBarberServiceItemBinding.inflate(layoutInflater, parent, false)
        return BarberServiceViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: BarberServiceViewHolder, position: Int) {
        holder.apply {
            val barberService = barbers[position]
            bind(barberService)
            itemView.setOnClickListener {
                var serviceid = barberService.serviceId.toString()
                var serviceName = barberService.serviceName.toString()
                saveserviceId(serviceid, serviceName)
                context.startActivity(Intent(context, TimeSelectionActivity::class.java))

                /*binding.apply {
                    serviceCheckbox.setOnClickListener {
                        barberService.isSelected = serviceCheckbox.isChecked
                    }
                }*/
            }
        }
    }
    private fun  saveserviceId(serviceid: String, serviceName:String) {
        val pref = context.getSharedPreferences("users", AppCompatActivity.MODE_PRIVATE)
        pref.edit().apply{
            putString("service_id", serviceid)
            putString("service_name", serviceName)
            apply()
        }
    }
    inner class BarberServiceViewHolder(private val v: View) : RecyclerView.ViewHolder(v) {
        fun bind(barberService: BarberService) {
            val name = v.findViewById<TextView>(R.id.tv_service_name)
            val cost = v.findViewById<TextView>(R.id.tv_duration)
            val duration = v.findViewById<TextView>(R.id.tv_cost)
            val image = v.findViewById<AppCompatImageView>(R.id.service_image)
            name.text = barberService.serviceName
            cost.text = barberService.cost.toString()
            duration.text = barberService.duration.toString()
            Glide.with(context)
                .load(BASE_IMAGE_URL + barberService.servicePic)
                .into(image)
        }
    }
}
