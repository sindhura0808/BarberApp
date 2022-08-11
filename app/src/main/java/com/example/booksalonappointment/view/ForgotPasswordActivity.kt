package com.example.booksalonappointment.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
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
    var name by remember { mutableStateOf("") }
    var phonenumber by remember { mutableStateOf("") }

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
            value = name,
            onValueChange = {
                name = it
            },
            placeholder = {
                Text(text = "Enter your name")
            },
            shape = RoundedCornerShape(24.dp),
        )

        Spacer(modifier = Modifier.width(10.dp))

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
            shape = RoundedCornerShape(24.dp),
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
                Text(text = "Enter your password")
            },
            shape = RoundedCornerShape(24.dp),
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
                Text(text = "Confirm your password")
            },
            shape = RoundedCornerShape(24.dp),
        )


        Spacer(modifier = Modifier.width(10.dp))

        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp),
            content = { Text(text = "Register") },
            onClick = {
                Toast.makeText(context, "Registration in successfully", Toast.LENGTH_SHORT).show()
            }
        )


    }

}

