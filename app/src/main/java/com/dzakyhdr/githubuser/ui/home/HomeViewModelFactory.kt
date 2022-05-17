package com.dzakyhdr.githubuser.ui.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dzakyhdr.githubuser.Injection
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

    companion object {
        @Volatile
        private var instance: HomeViewModelFactory? = null
        fun getInstance(
            context: Context,
            preference: SettingPreference
        ): HomeViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: HomeViewModelFactory(Injection.provideRepository(context), preference)
            }.also { instance = it }
    }
}