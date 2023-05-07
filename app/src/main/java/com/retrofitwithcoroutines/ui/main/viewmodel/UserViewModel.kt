package com.retrofitwithcoroutines.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.retrofitwithcoroutines.data.repository.UsersRepository
import com.retrofitwithcoroutines.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor (private val usersRepository: UsersRepository):ViewModel()
{

    fun getUsersList() = liveData(Dispatchers.IO)
    {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = usersRepository.getUserList()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}