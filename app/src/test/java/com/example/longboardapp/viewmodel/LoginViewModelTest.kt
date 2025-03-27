package com.example.longboardapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.longboardapp.domain.UserRepository
import com.example.longboardapp.model.UserModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class LoginViewModelTest {


    @Mock
    private lateinit var userRepository: UserRepository

    private lateinit var loginViewModel: LoginViewModel

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup(){
        MockitoAnnotations.openMocks(this)
        loginViewModel = LoginViewModel(userRepository)
    }


    @Test
    fun `onLoginChanged sets email and password`()  {
        val email = "test@example.com"
        val password = "password123"

        loginViewModel.onLoginChanged(email, password)

        assertEquals(email, loginViewModel._email.value)
        assertEquals(password, loginViewModel._password.value)
    }

    @Test
    fun `onLoginChanged enables login with valid credentials`() {
        val email = "test@example.com"
        val password = "password123"

        loginViewModel.onLoginChanged(email, password)

        assertEquals(true, loginViewModel.loginEnable.value)
    }

    @Test
    fun `onLoginChanged disables login with invalid email`() {
        val email = "invalid_email"
        val password = "password123"

        loginViewModel.onLoginChanged(email, password)

        assertEquals(false, loginViewModel.loginEnable.value)
    }

    @Test
    fun `onLoginChanged disables login with short password`() {
        val email = "test@example.com"
        val password = "pass"

        loginViewModel.onLoginChanged(email, password)

        assertEquals(false, loginViewModel.loginEnable.value)
    }

    @Test
    fun `onLoginSelected sets isLoading and calls insertUser`() = runTest {
        val email = "test@example.com"
        val password = "password123"
        loginViewModel.onLoginChanged(email, password)

        loginViewModel.onLoginSelected()

        verify(userRepository).insertUser(UserModel(email, password))
        assertEquals(false, loginViewModel.isLoading.value)
    }

    @Test
    fun `initial values are null`(){
        assertEquals(null, loginViewModel.email.value)
        assertEquals(null, loginViewModel.password.value)
        assertEquals(null, loginViewModel.loginEnable.value)
        assertEquals(null, loginViewModel.isLoading.value)
    }
}