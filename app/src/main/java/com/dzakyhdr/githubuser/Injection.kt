package com.dzakyhdr.githubuser

import android.content.Context
import com.dzakyhdr.githubuser.data.local.UserDatabase
import com.dzakyhdr.githubuser.data.repository.UserLocalDataSource
import com.dzakyhdr.githubuser.data.repository.UserRemoteDataSource
import com.dzakyhdr.githubuser.data.repository.UserRepository

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val database = UserDatabase.getInstance(context)
        val dao = database.userDao()
        val remoteDataSource = UserRemoteDataSource()
        val localDataSource = UserLocalDataSource(dao)
        return UserRepository.getInstance(remoteDataSource, localDataSource)
    }
}