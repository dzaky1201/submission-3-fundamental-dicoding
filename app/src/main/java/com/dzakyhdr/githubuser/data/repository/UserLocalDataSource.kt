package com.dzakyhdr.githubuser.data.repository

import com.dzakyhdr.githubuser.data.local.UserDao
import com.dzakyhdr.githubuser.data.local.UserEntity
import com.dzakyhdr.githubuser.data.remote.model.UserItem

class UserLocalDataSource(private val userDao: UserDao) {

    suspend fun insertUser(user: UserItem) {
        val userEntity = UserEntity(
            user.login ?: "",
            user.avatarUrl,
            user.company,
            user.followers,
            user.following,
            user.location,
            user.name,
            user.publicRepos,
            user.id
        )
        userDao.insertUser(userEntity)
    }

    suspend fun getUsers(): List<UserEntity> {
        return userDao.getUser()
    }

    suspend fun delete(user: UserItem) {
        val userEntity = UserEntity(
            user.login ?: "",
            user.avatarUrl,
            user.company,
            user.followers,
            user.following,
            user.location,
            user.name,
            user.publicRepos,
            user.id
        )
        userDao.deleteUser(userEntity)
    }
}