package com.example.booksalonappointment.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.booksalonappointment.BuildConfig
import com.example.booksalonappointment.R
import com.example.booksalonappointment.databinding.ActivityAboutUsactivityBinding

class AboutUSActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutUsactivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityAboutUsactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            backService.setOnClickListener {
                super.onBackPressed()
                finish()
            }
        }

        binding.btnCallUs.setOnClickListener {
            val uri: Uri = Uri.parse("tel:9080706050")
            val phoneIntent = Intent(Intent.ACTION_DIAL, uri)
            startActivity(phoneIntent)
        }

        binding.btnShare.setOnClickListener {
            val appID = BuildConfig.APPLICATION_ID
            val text = """ Hey! I am using Test 5G app to test my Internaet speed.
                Its easy and fun to use here is th link to download the app.
                https://play.google.com/store/apps/details?id=$appID
                
            """.trimIndent()
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "download speed Test 5G app")
            shareIntent.putExtra(Intent.EXTRA_TEXT, text)
            startActivity(shareIntent)
        }

        binding.btnYoutube.setOnClickListener {

            val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com"))
            startActivity(webIntent)
        }
    binding.btnLocateUs.setOnClickListener {
            val location = Uri.parse("geo:37.4219983,-122.084")

            val mapIntent = Intent(Intent.ACTION_VIEW, location)
            startActivity(mapIntent)
        }

    }

}