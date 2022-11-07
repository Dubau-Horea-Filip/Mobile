package com.example.animal_shelter.viewModels

import androidx.compose.material.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {

    private var username = "name";
    private var passeard = "1234"

    var UsernameInput by mutableStateOf(TextFieldValue(""))
    var PasswordInput by mutableStateOf(TextFieldValue(""))

    fun testCredentials( onLoginSucces: ()-> Unit)
    {
        if(username == UsernameInput.text && passeard == PasswordInput.text )
        {
            onLoginSucces()
        }
    }
}