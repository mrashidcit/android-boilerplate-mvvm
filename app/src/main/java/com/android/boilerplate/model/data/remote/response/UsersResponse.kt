package com.android.boilerplate.model.data.remote.response

import android.os.Parcelable
import com.android.boilerplate.base.model.data.remote.response.BaseResponse
import com.android.boilerplate.model.data.local.database.entities.User
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * @author Abdul Rahman
 */
@Parcelize
data class UsersResponse(
    @SerializedName("results")
    var results: List<User>? = null
) : Parcelable, BaseResponse()