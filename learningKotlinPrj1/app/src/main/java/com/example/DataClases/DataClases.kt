package com.example.DataClases

class DataClases {
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
}
