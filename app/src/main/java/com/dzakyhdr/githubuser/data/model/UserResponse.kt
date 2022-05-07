package com.dzakyhdr.githubuser.data.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("total_count")
    val totalCount: Int?,
    @SerializedName("items")
    val items: List<UserItem>?,
)
