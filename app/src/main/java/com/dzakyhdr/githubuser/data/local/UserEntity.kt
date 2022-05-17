package com.dzakyhdr.githubuser.data.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "user_favorite")
@Parcelize
data class UserEntity(
    @PrimaryKey
    val login: String = "",
    val avatarUrl: String?,
    val company: String?,
    val followers: Int?,
    val following: Int?,
    val location: String?,
    val name: String?,
    val publicRepos: Int?,
    val id: Int?
) : Parcelable