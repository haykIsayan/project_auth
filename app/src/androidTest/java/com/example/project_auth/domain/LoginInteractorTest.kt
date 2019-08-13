package com.example.project_auth.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.example.project_auth.AuthUtils
import com.example.project_auth.NoUserFoundResource
import com.example.project_auth.PasswordIncorrectResource
import com.example.project_auth.data.UserDataSource
import com.example.project_auth.model.Resource
import com.example.project_auth.model.User
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
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
    fun test() {
        runBlocking {
            testNoUserFound()
            testLoginSuccess()
            testPasswordIncorrect()
        }
    }

    private suspend fun testNoUserFound() {
        mLoginUserInteractor.enablePending(false)
        val data = mLoginUserInteractor.test()
        val resource = data.value

        assertNotNull(resource)
        assertTrue(resource is NoUserFoundResource)
    }

    private suspend fun testLoginSuccess() {
        mUserDataSource.insertUser(mUser)

        val data = mLoginUserInteractor.test()
        val resource = data.value

        assertNotNull(resource)
        assertTrue(resource is Resource.SuccessResource)
        val resultUser = (resource as Resource.SuccessResource).data
        assertTrue(validateUser(resultUser))
    }

    private suspend fun testPasswordIncorrect() {
        mLoginUserInteractor = LoginUserInteractor(mUser.userName, "asdf", mUserDataSource)

        val data = mLoginUserInteractor.test()
        val resource = data.value

        assertNotNull(resource)
        assertTrue(resource is PasswordIncorrectResource)
    }

    private fun validateUser(resultUser: User) =
            resultUser.userName == mUser.userName
                    && resultUser.password == mUser.password
                    && resultUser.firstName == mUser.firstName
                    && resultUser.lastName == mUser.lastName
}