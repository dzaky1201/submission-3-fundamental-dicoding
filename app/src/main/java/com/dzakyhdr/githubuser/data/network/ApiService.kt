package com.dzakyhdr.githubuser.data.network

import com.dzakyhdr.githubuser.BuildConfig
import com.dzakyhdr.githubuser.data.model.UserItem
import com.dzakyhdr.githubuser.data.model.UserResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/users")
    @Headers(
        "Authorization: token ${BuildConfig.API_KEY}",
    )
    suspend fun getSearchUser(
        @Query("q") query: String
    ): Response<UserResponse>

    @GET("users/{username}")
    @Headers(
        "Authorization: token ${BuildConfig.API_KEY}",
    )
    suspend fun getDetail(
        @Path("username") username: String
    ): Response<UserItem>

    @GET("users/{username}/following")
    @Headers(
        "Authorization: token ${BuildConfig.API_KEY}",
    )
    suspend fun getFollowingUsers(
        @Path("username") username: String
    ): Response<List<UserItem>>

    @GET("users/{username}/followers")
    @Headers(
        "Authorization: token ${BuildConfig.API_KEY}",
    )
    suspend fun getFollowersUsers(
        @Path("username") username: String
    ): Response<List<UserItem>>
}

class ApiConfig {
    companion object {
        fun getApiService(): ApiService {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }

}