package com.dzakyhdr.githubuser.ui.following

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dzakyhdr.githubuser.Injection
import com.dzakyhdr.githubuser.data.repository.UserRepository

class FollowingViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FollowingViewModel::class.java)) {
            return FollowingViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

    companion object {
        @Volatile
        private var instance: FollowingViewModelFactory? = null
        fun getInstance(
            context: Context
        ): FollowingViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: FollowingViewModelFactory(Injection.provideRepository(context))
            }.also { instance = it }
    }
}