package com.safetyinoffice.safety.util

import com.safetyinoffice.safety.home.response.CreateSession
import com.safetyinoffice.safety.home.response.InSessionResponse
import com.safetyinoffice.safety.home.response.StopResponse
import com.safetyinoffice.safety.loginRegister.response.LoginResponse
import com.safetyinoffice.safety.loginRegister.response.RegisterResponse
import com.safetyinoffice.safety.loginRegister.response.User
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface Apis {


    @GET("users/login")
    fun loginUser(@Header("email") email: String, @Header("password") password: String, @Header("deviceId") deviceId: String): Call<LoginResponse>

    @POST("users/register")
    fun registerUser(@Body userData: User): Call<RegisterResponse>

    /*
        @Headers("Content-Type: application/json")
    */
    @POST("users/forgot-password")
    fun forgetPassword(@Body userData: User): Call<ResponseBody>

    /*
        @Headers("Content-Type: application/json")
    */
    @GET("users/getOfficesDetail")
    fun getOfficesDetail(@Header("Authorization") auth_token: String): Call<InSessionResponse>


    @Headers("Content-Type: application/json")
    @POST("users/Session")
    fun createSession(@Header("Authorization") auth_token: String, @Body userData: CreateSession): Call<ResponseBody>

    @Headers("Content-Type: application/json")
    @POST("users/Session")
    fun stopSession(@Header("Authorization") auth_token: String, @Body stopSession: StopResponse): Call<ResponseBody>
}