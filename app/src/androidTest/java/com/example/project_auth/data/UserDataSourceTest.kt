package com.example.project_auth.data

import androidx.test.core.app.ApplicationProvider
import com.example.project_auth.AuthUtils
import com.example.project_auth.model.User
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class UserDataSourceTest {

    private lateinit var mUserDataSource: UserDataSource
    private val mUser = AuthUtils.getDummieUser()

    @Before
    fun setup() {
        mUserDataSource = UserDataSource(ApplicationProvider.getApplicationContext())
                .setTestable()
    }

    @After
    fun tearDown() {
        runBlocking {
            mUserDataSource.clearRepository()
        }
    }

    @Test
    fun test() {
        testInsertUser()
        testGetUser()
        testGetUsers()
    }

    private fun testInsertUser() = runBlocking {
        val id = mUserDataSource.insertUser(mUser)
        assertNotNull(id)
        assertTrue(id == 1L)
    }

    private fun testGetUser() = runBlocking {
        val user = mUserDataSource.getUser(mUser.userName)
        assertNotNull(user)
        assertTrue(user?.userName == mUser.userName)
    }

    private fun testGetUsers() = runBlocking {
        val users = mUserDataSource.getUsers()
        assertNotNull(users)
        assertTrue(users!!.isNotEmpty())
    }
}