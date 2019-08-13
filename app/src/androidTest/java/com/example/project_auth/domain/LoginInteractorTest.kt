package com.example.project_auth.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.example.project_auth.AuthUtils
import com.example.project_auth.data.UserDataSource
import com.example.project_auth.model.Resource
import com.example.project_auth.model.User
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LoginInteractorTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var mUserDataSource: UserDataSource
    private lateinit var mLoginUserInteractor: LoginUserInteractor
    private val mUser: User = AuthUtils.getDummieUser()

    @Before
    fun setup() {
        mUserDataSource = UserDataSource(ApplicationProvider.getApplicationContext())
        val user = AuthUtils.getDummieUser()
        mLoginUserInteractor = LoginUserInteractor(user.userName, user.password, mUserDataSource)
    }

    @After
    fun tearDown() {
        runBlocking {
            mUserDataSource.clearRepository()
        }
    }

    @Test
    fun testLoginInteractor() {
        runBlocking {
            testLoginFailed()
            testLoginSuccess()
        }
    }

    private suspend fun testLoginFailed() {
        mLoginUserInteractor.enablePending(false)
        val data = mLoginUserInteractor.test()

        assertNotNull(data)
        val resource = data.value
        assertTrue(resource is Resource.FailResource)
        assertTrue((resource as Resource.FailResource).throwable.message == "FUCK")
    }

    private suspend fun testLoginSuccess() {
        mUserDataSource.insertUser(mUser)

        val data = mLoginUserInteractor.test()

        assertNotNull(data)
        val resource = data.value
        assertTrue(resource is Resource.SuccessResource)
        val resultUser = (resource as Resource.SuccessResource).data
        assertTrue(validateUser(resultUser))
    }


    fun testTransferToLiveData() {

    }

    private fun validateUser(resultUser: User) =
            resultUser.userName == mUser.userName
                    && resultUser.password == mUser.password
                    && resultUser.firstName == mUser.firstName
                    && resultUser.lastName == mUser.lastName

}