package com.android.boilerplate.model.data.aide

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * @author Abdul Rahman
 */
@Parcelize
data class Language(
    @SerializedName("id")
    val id: Int,
    @SerializedName("lang")
    val lang: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("selected")
    var selected: Boolean = false
) : Parcelable
