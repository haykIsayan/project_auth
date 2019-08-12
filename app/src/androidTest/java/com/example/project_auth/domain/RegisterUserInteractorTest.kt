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

class RegisterUserInteractorTest {

    private lateinit var mUserDataSource: UserDataSource
    private lateinit var mRegisterUserInteractor: RegisterUserInteractor

    private val mUser = AuthUtils.getDummieUser()

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
            testRegisterSuccess()
            testRegisterFailed()
        }
    }


    private suspend fun testRegisterFailed() {
        val data = mRegisterUserInteractor.run()

        assertNotNull(data)
        assertTrue(data is Resource.FailResource)
    }

    private suspend fun testRegisterSuccess() {
        val data = mRegisterUserInteractor.run()

        assertNotNull(data)
        assertTrue(data is Resource.SuccessResource)
    }

}