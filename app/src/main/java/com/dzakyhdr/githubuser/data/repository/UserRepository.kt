package com.dzakyhdr.githubuser.data.repository

import com.dzakyhdr.githubuser.data.model.UserItem

class UserRepository(private val remoteDataSource: UserRemoteDataSource) {

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
}