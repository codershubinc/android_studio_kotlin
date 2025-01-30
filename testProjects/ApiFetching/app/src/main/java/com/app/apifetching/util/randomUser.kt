package com.app.apifetching.util

import android.widget.TextView
import com.models.randomUser.RandomUser
import com.utils.apiFetch.ApiFetchUtil
import retrofit2.Response
import retrofit2.http.GET

class RandomUser {


    interface MyApi {
        @GET("random_user") // Replace with your endpoint
        suspend   fun getRandomUser(): Response<RandomUser.ApiResponse>
    }


    suspend  fun fetchUser(baseUrl: String , textView: TextView): RandomUser.User? {
        textView.text = "fetching ..."
        val  rt = ApiFetchUtil().createApi(
            baseUrl
        )
        val api = rt.create(MyApi::class.java)
        return try {
            val response = api.getRandomUser()
            if (response.isSuccessful) {
                textView.text = response.body()?.data?.user?.name
                response.body()?.data?.user
            } else {
                textView.text = "Error: ${response.code()}"
                null
            }
        } catch (e: Exception) {
            textView.text = "Error: ${e.message}"
            null
        }
    }

}