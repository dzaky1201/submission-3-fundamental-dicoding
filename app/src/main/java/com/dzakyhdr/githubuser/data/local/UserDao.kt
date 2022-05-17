package com.dzakyhdr.githubuser.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT * FROM user_favorite")
    suspend fun getUser(): List<UserEntity>

    @Delete
    suspend fun deleteUser(user: UserEntity)
}