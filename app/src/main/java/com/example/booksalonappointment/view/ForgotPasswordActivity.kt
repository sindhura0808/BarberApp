package com.example.booksalonappointment.view

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.unit.dp
import com.example.booksalonappointment.view.ui.theme.BookSalonAppointmentTheme

class ForgotPasswordActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookSalonAppointmentTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ForgotPassWordPreview()
                }
            }
        }
    }


    @Composable
    fun ForgotPassWordPreview() {
        ForgotScreenUI()
    }
}

@Composable
fun ForgotScreenUI() {

    val context = LocalContext.current
    var phonenumber by remember { mutableStateOf("") }
    var otp by remember { mutableStateOf("") }

    var password by remember { mutableStateOf("") }
    var confirmpassword by remember { mutableStateOf("") }
    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxSize())
    {

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp),
            value = phonenumber,
            onValueChange = {
                phonenumber= it
            },
            placeholder = {
                Text(text = "Enter your Phone number")
            },
         
        )

        Spacer(modifier = Modifier.width(10.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp),
            value =otp,
            onValueChange = {
                otp= it
            },
            placeholder = {
                Text(text = "Enter your OTP")
            },
          
        )

        Spacer(modifier = Modifier.width(10.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp),
            value = password,
            onValueChange = {
                password = it
            },
            placeholder = {
                Text(text = "Enter your  new password")
            },
       
        )


        Spacer(modifier = Modifier.width(10.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp),
            value = confirmpassword,
            onValueChange = {
                confirmpassword = it
            },
            placeholder = {
                Text(text = "Confirm your new password")
            },
            
        )


        Spacer(modifier = Modifier.width(10.dp))

        Button( modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp),
            content = { Text(text = "Submit") },
            onClick = {
                Toast.makeText(context, "Password changed successfully", Toast.LENGTH_SHORT).show()
            },

        )


    }

}

