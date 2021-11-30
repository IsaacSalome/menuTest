package com.example.menutest.ClimaFolder

import retrofit2.Call
import retrofit2.http.*

interface APIServices {
    @POST("clima")
    fun clima(@Body climaitem: ClimaItem) : Call<ClimaItem>


    @GET("clima")
    fun clima() : Call<List<ClimaItem>>

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "clima", hasBody = true)
    fun deleteclimaItem(@Field("id") id: Int): Call<DeleteResponse>
}

data class DeleteResponse(var response: String)