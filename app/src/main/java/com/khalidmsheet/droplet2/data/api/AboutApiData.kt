package com.khalidmsheet.droplet2.data.api

import com.google.gson.annotations.SerializedName

data class AboutApiData(
    @SerializedName("built")
    var built: String? = null
)
