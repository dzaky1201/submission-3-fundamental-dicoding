package com.dzakyhdr.githubuser.ui.followers

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dzakyhdr.githubuser.Injection
import com.dzakyhdr.githubuser.data.repository.UserRepository

class FollowersViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FollowersViewModel::class.java)) {
            return FollowersViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

    companion object {
        @Volatile
        private var instance: FollowersViewModelFactory? = null
        fun getInstance(
            context: Context
        ): FollowersViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: FollowersViewModelFactory(Injection.provideRepository(context))
            }.also { instance = it }
    }
}