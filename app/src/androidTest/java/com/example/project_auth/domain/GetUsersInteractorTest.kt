package com.example.project_auth.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.example.project_auth.AuthUtils
import com.example.project_auth.UserListEmptyResource
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
class GetUsersInteractorTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val mUser = AuthUtils.getDummieUser()
    private lateinit var mUserDataSource: UserDataSource
    private lateinit var mGetUsersInteractor: GetUsersInteractor

    @Before
    fun setUp() {
        mUserDataSource = UserDataSource(ApplicationProvider.getApplicationContext())
        mGetUsersInteractor = GetUsersInteractor(mUserDataSource)
    }


    @After
    fun tearDown() {
        runBlocking { mUserDataSource.clearRepository() }
    }

    @Test
    fun test() {
        runBlocking {
            testGetUsersFailed()
            testGetUsersSuccess()
        }
    }

    private suspend fun testGetUsersFailed() {
        val data = mGetUsersInteractor.test()

        // Test Resource
        assertNotNull(data.value)
        assertTrue(data.value is UserListEmptyResource)
    }

    private suspend fun testGetUsersSuccess() {
        mUserDataSource.insertUser(AuthUtils.getDummieUser())

        val data = mGetUsersInteractor.test()

        // Test UseCase Resource
        val resource = data.value
        assertNotNull(resource)
        assertTrue(resource is Resource.SuccessResource)
        // Test Resource Data
        val userList = (resource as Resource.SuccessResource).data
        assertTrue(userList.isNotEmpty())
        assertTrue(validateUser(userList[0]))
    }

    private fun validateUser(resultUser: User) =
            resultUser.userName == mUser.userName
                    && resultUser.password == mUser.password
                    && resultUser.firstName == mUser.firstName
                    && resultUser.lastName == mUser.lastName
}