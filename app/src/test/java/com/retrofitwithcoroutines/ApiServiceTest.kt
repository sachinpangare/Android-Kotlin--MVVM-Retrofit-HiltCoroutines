package com.retrofitwithcoroutines

import com.google.gson.Gson
import com.retrofitwithcoroutines.data.api.ApiHelper
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiServiceTest{
    lateinit var mockWebServer: MockWebServer

    lateinit var apiHelper: ApiHelper
    lateinit var gson: Gson

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        gson = Gson()
        mockWebServer = MockWebServer()
        apiHelper = Retrofit.Builder()
            .baseUrl(mockWebServer.url("https://jsonplaceholder.typicode.com/photos"))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(ApiHelper::class.java)
    }

    @Test
    fun `get all photo api test`() {
        runBlocking {
            val mockResponse = MockResponse()
            mockWebServer.enqueue(mockResponse.setBody("[]"))
            val response = apiHelper.getUserList()
            val request = mockWebServer.takeRequest()
            assertEquals(true, response.isEmpty())
        }
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }
}
