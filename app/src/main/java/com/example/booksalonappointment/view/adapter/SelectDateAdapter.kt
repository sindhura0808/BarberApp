package com.example.booksalonappointment.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

import com.example.booksalonappointment.R

import com.example.booksalonappointment.databinding.ViewBookSelectDateBinding

import com.example.booksalonappointment.model.remote.response.time.Slot

import com.example.booksalonappointment.viewmodel.timeselection.TimeSelectionViewModel

class SelectDateAdapter(
    private val context: Context,
    val infoList: List<Slot>,
    val viewModel: TimeSelectionViewModel,
    private val tv: TextView
) :
    RecyclerView.Adapter<SelectDateAdapter.SelectDateHolder>() {

    lateinit var binding: ViewBookSelectDateBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SelectDateAdapter.SelectDateHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ViewBookSelectDateBinding.inflate(layoutInflater, parent, false)
        return SelectDateHolder(binding.root)
    }

    override fun onBindViewHolder(holder: SelectDateHolder, position: Int) {
        holder.apply {
            val date = infoList[position]
            holder.bind(date)

          /*  itemView.setOnClickListener {
                val selectedDate = date.date
                val selectedDay = date.day
                val pref = context.getSharedPreferences("users", AppCompatActivity.MODE_PRIVATE)
                pref.edit().apply {
                    putString("selectedDate", selectedDate)
                    putString("selectedDay", selectedDay)
                    apply()
                }
            }*/
        }
    }

    override fun getItemCount() = infoList.size

    inner class SelectDateHolder(private val v: View) : RecyclerView.ViewHolder(v) {
        fun bind(slot: Slot) {
            val dateSplit = slot.date.split("-")
            val month = getMothFromInt(dateSplit[1].toInt())
            val selectedDate = "$month ${dateSplit[2]}"
            binding.tvDate.text = selectedDate
            binding.tvDay.text = slot.day

            val pref=context.getSharedPreferences("users",AppCompatActivity.MODE_PRIVATE)
            pref.edit().apply{
                putString("selected_Date" , selectedDate)
                apply()
            }
            viewModel.appointmentsDateLiveData.observe(context as AppCompatActivity) {
                if (slot.date == it) {
                    binding.date.setBackgroundResource(R.drawable.available)
                    binding.date.setBackgroundColor(
                        ContextCompat.getColor(
                            context,
                            R.color.gray
                        )
                    )
                } else {
                    binding.date.setBackgroundResource(R.drawable.booked)
                    binding.date.setBackgroundColor(
                        ContextCompat.getColor(
                            context,
                            R.color.grey
                        )
                    )
                }
            }
            binding.root.setOnClickListener {
                val date = "\n${slot.day}, $selectedDate"
                tv.text = date
                viewModel.setAppointmentsDate(slot.date)
                viewModel.setAppointmentsStartFrom(-1)
                val pref = context.getSharedPreferences("users", AppCompatActivity.MODE_PRIVATE)
                pref.edit().apply {
                    putString("date", date)
                    apply()
                }
            }
        }
            private fun getMothFromInt(monthInt: Int): String {
                when (monthInt) {
                    1 -> return "Jan"
                    2 -> return "Feb"
                    3 -> return "Mar"
                    4 -> return "Apr"
                    5 -> return "May"
                    6 -> return "June"
                    7 -> return "July"
                    8 -> return "Aug"
                    9 -> return "Sep"
                    10 -> return "Oct"
                    11 -> return "Nov"
                    12 -> return "Dec"
                }
                return "Jan"
            }

        }

    }


