package com.retrofitwithcoroutines

import com.retrofitwithcoroutines.data.api.ApiService
import com.retrofitwithcoroutines.data.model.User
import com.retrofitwithcoroutines.data.repository.UsersRepository
import com.retrofitwithcoroutines.utils.Resource
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class UsersRepositoryTest {

    lateinit var repository: UsersRepository

    @Mock
    lateinit var apiService: ApiService

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repository = UsersRepository(apiService)
    }

    @Test
    fun `get all photo test`() {
        runBlocking {
            Mockito.`when`(apiService.getUserList()).thenReturn(listOf<User>())
            val response = repository.getUserList()
            assertEquals(listOf<User>(),  Resource.success(response).data)
        }

    }

}