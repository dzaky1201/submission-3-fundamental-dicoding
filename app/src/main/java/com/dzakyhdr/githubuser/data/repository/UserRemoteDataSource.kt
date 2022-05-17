package com.dzakyhdr.githubuser.data.repository

import com.dzakyhdr.githubuser.data.remote.model.UserItem
import com.dzakyhdr.githubuser.data.remote.network.ApiConfig

class UserRemoteDataSource {


    suspend fun getSearchUsers(username: String): List<UserItem>?{
        try {
            return ApiConfig.getApiService().getSearchUser(username).body()?.items
        } catch (cause: Throwable){
            throw  ErrorLoadData("Terjadi Kesalahan Saat Load Data", cause)
        }
    }

    suspend fun getFollowing(username: String): List<UserItem>? {
        try {
            return ApiConfig.getApiService().getFollowingUsers(username).body()
        } catch (cause: Throwable){
            throw  ErrorLoadData("Terjadi Kesalahan Saat Load Data", cause)
        }
    }

    suspend fun getDetail(username: String): UserItem? {
        try {
            return ApiConfig.getApiService().getDetail(username).body()
        } catch (cause: Throwable){
            throw  ErrorLoadData("Terjadi Kesalahan Saat Load Data", cause)
        }
    }

    suspend fun getFollowers(username: String): List<UserItem>? {
        try {
            return ApiConfig.getApiService().getFollowersUsers(username).body()
        } catch (cause: Throwable){
            throw  ErrorLoadData("Terjadi Kesalahan Saat Load Data", cause)
        }
    }
}

class ErrorLoadData(message: String, cause: Throwable?): Throwable(message, cause)