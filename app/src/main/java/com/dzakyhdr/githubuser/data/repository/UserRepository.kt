package com.dzakyhdr.githubuser.data.repository

import com.dzakyhdr.githubuser.data.local.UserEntity
import com.dzakyhdr.githubuser.data.remote.model.UserItem

class UserRepository(
    private val remoteDataSource: UserRemoteDataSource,
    private val userLocalDataSource: UserLocalDataSource
) {

    suspend fun getSearchUsers(username: String): List<UserItem>? {
        return remoteDataSource.getSearchUsers(username)
    }

    suspend fun getDetail(username: String): UserItem? {
        return remoteDataSource.getDetail(username)
    }

    suspend fun getFollowingUsers(username: String): List<UserItem>? {
        return remoteDataSource.getFollowing(username)
    }

    suspend fun getFollowersUsers(username: String): List<UserItem>? {
        return remoteDataSource.getFollowers(username)
    }

    suspend fun insertUser(user: UserItem) {
        userLocalDataSource.insertUser(user)
    }

    suspend fun getUsers(): List<UserEntity> {
        return userLocalDataSource.getUsers()
    }

    suspend fun delete(user: UserItem) = userLocalDataSource.delete(user)

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            remoteDataSource: UserRemoteDataSource,
            userLocalDataSource: UserLocalDataSource
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(remoteDataSource, userLocalDataSource)
            }.also { instance = it }
    }
}