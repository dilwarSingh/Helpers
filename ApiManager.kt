package com.safetyinoffice.safety.util

import com.safetyinoffice.safety.BuildConfig
import okhttp3.Interceptor
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException


class ApiManager {
    companion object {


        fun getApiManager(): Apis {

            val client = OkHttpClient.Builder()

                val logInterceptor = HttpLoggingInterceptor()
                logInterceptor.level = HttpLoggingInterceptor.Level.BODY
                client.addInterceptor(logInterceptor)


            client.addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val request = chain.request().newBuilder().addHeader("Content-Type", "application/json").build()
                    return chain.proceed(request)
                }
            })


            val retrofit = Retrofit.Builder()
                .baseUrl("http://13.233.193.12:3009/")
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create<Apis>(Apis::class.java)
        }
    }
}
