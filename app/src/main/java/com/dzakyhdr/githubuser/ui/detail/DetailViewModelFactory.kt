package com.dzakyhdr.githubuser.ui.detail

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dzakyhdr.githubuser.Injection
import com.dzakyhdr.githubuser.data.repository.UserRepository

class DetailViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

    companion object {
        @Volatile
        private var instance: DetailViewModelFactory? = null
        fun getInstance(
            context: Context
        ): DetailViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: DetailViewModelFactory(Injection.provideRepository(context))
            }.also { instance = it }
    }
}