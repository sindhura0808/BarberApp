package com.example.booksalonappointment.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.booksalonappointment.R
import com.example.booksalonappointment.databinding.FragmentHomeBinding

class HomeFragment: Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpEvents()
    }

    private fun setUpEvents() {
      binding.apply{
          btnBookAppointment.setOnClickListener {  }
          btnOurServices.setOnClickListener { startActivity(Intent(context,ServiceActivity::class.java)) }
          btnWorkinghours.setOnClickListener {   startActivity(Intent(context, WorkingHoursActivity::class.java))}
          btnAboutUs.setOnClickListener {   startActivity(Intent(context, AboutUSActivity::class.java))}
      }
    }

}