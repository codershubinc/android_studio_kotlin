package com.example.utils

import com.models.randomUser.RandomUser
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class ApiFetchUtil {


    interface MyApi {
        @GET("random_user") // Replace with your endpoint
        suspend fun getRandomUser(): Response<RandomUser.ApiResponse>
    }

    private fun createApi(baseUrl: String): MyApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                    .build()
            )
            .build()

        return retrofit.create(MyApi::class.java)
    }

    suspend fun fetchUser(baseUrl: String): RandomUser.User? {
        val api = createApi(baseUrl)
        return try {
            val response = api.getRandomUser()
            if (response.isSuccessful) {
                response.body()?.data?.user
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }
}
