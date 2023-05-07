package com.retrofitwithcoroutines.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.retrofitwithcoroutines.data.api.ApiService
import com.retrofitwithcoroutines.data.repository.UsersRepository
import com.retrofitwithcoroutines.ui.main.viewmodel.UserViewModel

class ViewModelFactory(private val apiService: ApiService) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(UsersRepository(apiService)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}
