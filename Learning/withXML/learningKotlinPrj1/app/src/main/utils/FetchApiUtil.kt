data class MyDataClass(
    val id: Int,
    val name: String,
    // ... other fields
)
interface MyApi {
    @GET("users") // Replace with your endpoint path
    suspend fun getUsers(): Response<List<MyDataClass>>
}
val retrofit = Retrofit.Builder()
    .baseUrl("https://your-api-base-url.com/") // Replace with your API base URL
    .addConverterFactory(GsonConverterFactory.create())
    .client(
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    )
    .build()

