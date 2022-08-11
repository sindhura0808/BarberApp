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
import com.example.booksalonappointment.databinding.BarberItemBinding
import com.example.booksalonappointment.databinding.ServiceItemBinding
import com.example.booksalonappointment.model.remote.Constants.BASE_IMAGE_URL
import com.example.booksalonappointment.model.remote.response.getbarber.Barber
import com.example.booksalonappointment.model.remote.response.getbarber.GetBarber
import com.example.booksalonappointment.model.remote.response.service.ServiceCategoryResponse
import com.example.booksalonappointment.view.BarberServiceActivity
import com.example.booksalonappointment.viewmodel.getbarber.GetBarberViewModel

class GetBarberAdapter(private var context: Context, private var barber: GetBarber,private val viewModel: GetBarberViewModel):
    RecyclerView.Adapter<GetBarberAdapter.BarberViewHolder>() {

    lateinit var binding: BarberItemBinding

    override fun getItemCount() = barber.barbers.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BarberViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        binding = BarberItemBinding.inflate(layoutInflater, parent, false)
        return BarberViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: BarberViewHolder, position: Int) {
        val bar= barber.barbers[position]
        holder.apply{
           bind(bar)
            itemView.setOnClickListener{
                var barberid=bar.barberId.toString()
                var barberName = bar.barberName.toString()
                savebarberid(barberid,barberName)
                context.startActivity(Intent(context, BarberServiceActivity::class.java))
                viewModel.selectedBarber.postValue(bar)
            }
        }
    }

    private fun savebarberid(barberid: String, barberName:String) {
        val pref = context.getSharedPreferences("users", AppCompatActivity.MODE_PRIVATE)
        pref.edit().apply{
            putString("barber_id", barberid)
            putString("barber_name", barberName)
            apply()
        }
    }


    inner class BarberViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(barber: Barber)
        { val tvCategory: TextView = binding.tvBarberName
            val rating=binding.rbRating
            tvCategory.text=barber.barberName
            rating.rating=barber.userRating.toFloat()
            Glide.with(context)
                .load(BASE_IMAGE_URL + barber.profilePic)
                .into(binding.ivBarberImage)

        }
    }


}