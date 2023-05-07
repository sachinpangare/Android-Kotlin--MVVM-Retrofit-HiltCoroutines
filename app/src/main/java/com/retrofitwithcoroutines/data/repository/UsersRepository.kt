package com.retrofitwithcoroutines.data.repository
import com.retrofitwithcoroutines.data.api.ApiService
import javax.inject.Inject

class UsersRepository @Inject constructor(private val apiService: ApiService)
{
 suspend fun getUserList() = apiService.getUserList()
}