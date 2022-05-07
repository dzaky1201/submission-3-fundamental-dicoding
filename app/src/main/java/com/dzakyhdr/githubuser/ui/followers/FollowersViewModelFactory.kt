package com.dzakyhdr.githubuser.ui.followers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dzakyhdr.githubuser.data.repository.UserRepository

class FollowersViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FollowersViewModel::class.java)) {
            return FollowersViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}