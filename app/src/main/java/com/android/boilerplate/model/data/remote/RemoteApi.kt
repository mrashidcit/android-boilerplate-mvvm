package com.android.boilerplate.model.data.remote

import com.android.boilerplate.model.data.remote.response.UsersResponse
import retrofit2.Response
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
}