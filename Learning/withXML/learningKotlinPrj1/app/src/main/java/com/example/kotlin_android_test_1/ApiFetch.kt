package com.example.kotlin_android_test_1

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.utils.ApiFetchUtil
import com.example.utils.CoilUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ApiFetch : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_api_fetch)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private suspend fun fetchAndDisplayUser() {
        val apiFetchUtil = ApiFetchUtil()
        val user = apiFetchUtil.fetchUser(
            "https://openapihub.vercel.app/v0.1/"
        )


        val userNameTextView = findViewById<TextView>(R.id.userName)
        val userEmailTextView = findViewById<TextView>(R.id.userEmail)


        if (user != null) {

            userNameTextView.text = user.name
            userEmailTextView.text = user.email

            val imageView = findViewById<ImageView>(R.id.userImage)
            CoilUtil.loadImageWithProgress(
                context = this,
                imageView = imageView,
                url = user.avatar,
                progressBar = findViewById(R.id.loadingUserImage)
            )


            // Optionally, load avatar into an ImageView using libraries like Glide or Coil
        } else {
            userNameTextView.text = "Failed to fetch user."
            userEmailTextView.text = ""
        }
    }

    fun fetchUser(view: View) {
        val loadingImage = findViewById<ProgressBar>(R.id.loadingImage)
        val userNameTextView = findViewById<TextView>(R.id.userName)
        val userEmailTextView = findViewById<TextView>(R.id.userEmail)
        val userImage = findViewById<ImageView>(R.id.userImage)

        // Show the loading image
        loadingImage.visibility = View.VISIBLE
        userNameTextView.visibility = View.GONE
        userEmailTextView.visibility = View.GONE
        userImage.visibility = View.GONE


        lifecycleScope.launch(Dispatchers.Main) {
            try {
                fetchAndDisplayUser()
            } catch (e: Exception) {
                val userNameTextView = findViewById<TextView>(R.id.userName)
                userNameTextView.text = "Error: ${e.message}"
            } finally {
                // Hide the loading image after the operation is complete
                loadingImage.visibility = View.GONE
                userNameTextView.visibility = View.VISIBLE
                userEmailTextView.visibility = View.VISIBLE
            }
        }
    }
}

