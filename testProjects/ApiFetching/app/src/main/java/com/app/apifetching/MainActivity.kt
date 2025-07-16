package com.codershubinc.apifetching // Corrected package

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope

import com.codershubinc.apifetching.R
import com.app.apifetching.util.RandomUser
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    @SuppressLint("SetTextI18n")
    fun fetch(view: View) {
lifecycleScope.launch {
    try {
        RandomUser().fetchUser (
            "https://openapihub.vercel.app/v0.1/",
            findViewById<TextView>(R.id.name)
        )
    }
    catch (e:Exception){
        findViewById<TextView>(R.id.name).text = "Error: ${e.message}"
    }
}


    }


}