package com.retrofitwithcoroutines.data.api
import com.retrofitwithcoroutines.data.model.User
import retrofit2.http.GET

interface ApiHelper {

    @GET("photos")
    suspend fun getUserList():List<User>
}