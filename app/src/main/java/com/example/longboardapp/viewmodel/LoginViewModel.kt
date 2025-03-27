package com.example.longboardapp.viewmodel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.longboardapp.domain.UserRepository
import com.example.longboardapp.model.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject
constructor(

    private val userRepository: UserRepository

) : ViewModel() {

    internal var _email = MutableLiveData<String>()
    var email: LiveData<String> = _email

    internal var _password = MutableLiveData<String>()
    var password: LiveData<String> = _password

    internal var _loginEnable = MutableLiveData<Boolean>()
    var loginEnable: LiveData<Boolean> = _loginEnable

    internal var _isLoading = MutableLiveData<Boolean>()
    var isLoading: LiveData<Boolean> = _isLoading

    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
        _loginEnable.value = isValidEmail(email) && isValidPassword(password)
    }

    private fun isValidPassword(password: String): Boolean = password.length > 6

    internal fun isValidEmail(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()

    suspend fun onLoginSelected() {

        _isLoading.value = true
        userRepository.insertUser(UserModel(email.value.toString(), password.value.toString()))
        delay(4000)
        _isLoading.value = false
        }




}