package com.android.boilerplate.model.data.local.database.entities

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * @author Abdul Rahman
 */
@Entity
@Parcelize
data class User(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("uid")
    var id: Int? = null,
    @SerializedName("gender")
    var gender: String? = null,
    @Embedded
    @SerializedName("name")
    var name: Name? = null,
    @Embedded
    @SerializedName("location")
    var location: Location? = null,
    @SerializedName("email")
    var email: String? = null,
    @SerializedName("phone")
    var phone: String? = null,
    @SerializedName("cell")
    var cell: String? = null,
    @Embedded
    @SerializedName("picture")
    var picture: Picture? = null,
) : Parcelable

@Parcelize
data class Name(
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("first")
    var first: String? = null,
    @SerializedName("last")
    var last: String? = null
) : Parcelable

@Parcelize
data class Location(
    @SerializedName("city")
    var city: String? = null,
    @SerializedName("state")
    var state: String? = null,
    @SerializedName("country")
    var country: String? = null,
    @SerializedName("postcode")
    var postcode: String? = null
) : Parcelable

@Parcelize
data class Picture(
    @SerializedName("large")
    var large: String? = null,
    @SerializedName("medium")
    var medium: String? = null,
    @SerializedName("thumbnail")
    var thumbnail: String? = null
) : Parcelable