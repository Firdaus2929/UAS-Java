package com.informatika.firdaushermawan_19100018_kalkulator

import com.informatika.firdaushermawan_19100018_kalkulator.model.ResponseActionBarang
import com.informatika.firdaushermawan_19100018_kalkulator.model.ResponseAdmin
import com.informatika.firdaushermawan_19100018_kalkulator.model.ResponseBarang
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiService {
    @GET("read.php")
    fun getBarang(): Call<ResponseBarang>

    @FormUrlEncoded
    @POST("create.php")
    fun insertBarang(
        @Field("bil1") bil1: String?,
        @Field("bil2") bil2: String?
    ): Call<ResponseActionBarang>

    @FormUrlEncoded
    @POST("update.php")
    fun updateBarang(
        @Field("id") id: String?,
        @Field("bil1") bil1: String?,
        @Field("bil2") bil2: String?,
        @Field("hasil") hasil: String?
    ): Call<ResponseActionBarang>

    @FormUrlEncoded
    @POST("delete.php")
    fun deleteBarang(
        @Field("id") id: String?
    ): Call<ResponseActionBarang>

    @FormUrlEncoded
    @POST("login.php")
    fun logIn(
        @Field("Username") Username : String?,
        @Field("Password") Password : String?
    ):Call<ResponseAdmin>

    @FormUrlEncoded
    @POST("register.php")
    fun register(
        @Field("Username") Username : String?,
        @Field("Password") Password : String?
    ):Call<ResponseAdmin>
}