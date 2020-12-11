package com.android.boilerplate.model.data.remote.request

import android.os.Parcelable
import com.android.boilerplate.base.model.data.remote.request.BaseRequest
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * @author Abdul Rahman
 */
@Parcelize
data class UsersRequest(
    @SerializedName("results")
    var results: Int? = null
) : Parcelable, BaseRequest()