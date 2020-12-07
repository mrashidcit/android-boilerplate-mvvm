package com.android.boilerplate.model.data.remote

import com.android.boilerplate.model.data.remote.response.UsersResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Abdul Rahman
 */
interface RemoteApi {

    @GET(EndPoint.GET_USERS)
    suspend fun getUsers(
        @Query("results") results: Int?
    ): Response<UsersResponse>

    companion object {

        private lateinit var remoteApi: RemoteApi

        fun getRemoteApi(): RemoteApi {
            if (!this::remoteApi.isInitialized) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://randomuser.me/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                remoteApi = retrofit.create(RemoteApi::class.java)
            }
            return remoteApi
        }
    }
}