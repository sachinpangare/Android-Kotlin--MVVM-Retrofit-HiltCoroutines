package com.retrofitwithcoroutines.data.api

import javax.inject.Inject


class ApiService @Inject constructor(val apiHelper: ApiHelper) {

    suspend fun getUserList() = apiHelper.getUserList()
}
