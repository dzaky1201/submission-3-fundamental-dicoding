package com.dzakyhdr.githubuser

import android.app.Application
import com.dzakyhdr.githubuser.data.repository.UserRemoteDataSource
import com.dzakyhdr.githubuser.data.repository.UserRepository

class MyApplication: Application() {

    private val userRemoteDataSource by lazy { UserRemoteDataSource() }
    val repository by lazy { UserRepository(userRemoteDataSource) }
}