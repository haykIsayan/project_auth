package com.example.project_auth.domain

import androidx.test.core.app.ApplicationProvider
import com.example.project_auth.AuthUtils
import com.example.project_auth.data.UserDataSource
import com.example.project_auth.model.Resource
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class AuthenticationTest {

    private val mUser = AuthUtils.getDummieUser()
    private lateinit var mUserDataSource: UserDataSource
    private lateinit var mLoginUserInteractor: LoginUserInteractor
    private lateinit var mRegisterUserInteractor: RegisterUserInteractor

    @Before
    fun setUp() {
        mUserDataSource = UserDataSource(ApplicationProvider.getApplicationContext())
        mRegisterUserInteractor = RegisterUserInteractor(mUser, mUserDataSource)
    }

    @After
    fun tearDown() {
        runBlocking {
            mUserDataSource.clearRepository()
        }
    }

    @Test
    fun test() {
        runBlocking {
            testRegisterThenLogin()
            testRegisterAgain()
        }
    }

    suspend fun testRegisterThenLogin() {
        val registerResource = mRegisterUserInteractor.run()

        assertNotNull(registerResource)
        assertTrue(registerResource is Resource.SuccessResource)
        val registerUser = (registerResource as Resource.SuccessResource).data

        mLoginUserInteractor = LoginUserInteractor(registerUser.userName, registerUser.password, mUserDataSource)
        val loginResource = mLoginUserInteractor.run()
        assertNotNull(loginResource is Resource.SuccessResource)
        val loginUser = (loginResource as Resource.SuccessResource).data
        assertTrue(loginUser.userName == registerUser.userName)
    }

    suspend fun testRegisterAgain() {
        val registerResource = mRegisterUserInteractor.run()

        assertNotNull(registerResource)
        assertTrue(registerResource is Resource.FailResource)
    }
}