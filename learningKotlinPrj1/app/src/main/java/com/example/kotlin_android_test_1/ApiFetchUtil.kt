import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class ApiFetchUtil {
    data class ApiResponse(
        val statusCode: Int,
        val data: UserData,
        val message: String,
        val success: Boolean
    )

    data class UserData(
        val user: User
    )

    data class User(
        val id: Int,
        val name: String,
        val email: String,
        val avatar: String
    )

    interface MyApi {
        @GET("random_user") // Replace with your endpoint
        suspend fun getRandomUser(): Response<ApiResponse>
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://openapihub.vercel.app/v0.1/") // Replace with your API base URL
        .addConverterFactory(GsonConverterFactory.create())
        .client(
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()
        )
        .build()

    private val api: MyApi = retrofit.create(MyApi::class.java)

    suspend fun fetchUser(): User? {
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
