package com.dzakyhdr.githubuser.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dzakyhdr.githubuser.SettingPreference
import com.dzakyhdr.githubuser.data.repository.UserRepository

class HomeViewModelFactory(
    private val repository: UserRepository,
    private val preference: SettingPreference
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository, preference) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}