package com.devtides.androidcoroutinesretrofit.model

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName(value = "name")
    val countryName: String?,
    @SerializedName(value = "capital")
    val capital: String?,
    @SerializedName(value = "flagPNG")
    val flag: String?
)