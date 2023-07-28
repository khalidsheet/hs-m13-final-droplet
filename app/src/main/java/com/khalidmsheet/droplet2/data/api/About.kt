package com.khalidmsheet.droplet2.data.api

import retrofit2.Response
import retrofit2.http.GET

interface About {
    @GET("8e49fe17feef48c2258b2a2b7bf3dd50/raw/7f115863659a9062f8aa2e329dec07788c4daa1d/about.json")
    suspend fun getAbout(): Response<AboutApiData>
}