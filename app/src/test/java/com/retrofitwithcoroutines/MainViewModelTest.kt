package com.retrofitwithcoroutines

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.retrofitwithcoroutines.data.api.ApiHelper
import com.retrofitwithcoroutines.data.model.User
import com.retrofitwithcoroutines.data.repository.UsersRepository
import com.retrofitwithcoroutines.ui.main.viewmodel.UserViewModel
import com.retrofitwithcoroutines.utils.Resource
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class MainViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()
    lateinit var mainViewModel: UserViewModel

    lateinit var mainRepository: UsersRepository

    @Mock
    lateinit var apiHelper: ApiHelper

    @get:Rule
    val instantTaskExecutionRule: InstantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        mainRepository = mock(UsersRepository::class.java)
        mainViewModel = UserViewModel(mainRepository)
    }


    @Test
    fun getAllPhotoTest() {
        runBlocking {
            Mockito.`when`(mainRepository.getUserList())
                .thenReturn(Resource.success((listOf<User>(User(1,2,"", "", "")))))
                        mainViewModel.getUsersList()
            val result = mainViewModel.getUsersList()
            assertEquals(listOf<User>(User(1,2,"", "", "")), result)
        }
    }


    @Test
    fun `empty photo list test`() {
        runBlocking {
            Mockito.`when`(mainRepository.getUserList())
                .thenReturn(Resource.success((listOf<User>())))
            mainViewModel.getUsersList()
            val result = mainViewModel.getUsersList()
            assertEquals(listOf<User>(), result)
        }
    }

}