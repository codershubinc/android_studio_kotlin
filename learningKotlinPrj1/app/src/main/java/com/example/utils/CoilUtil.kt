package com.example.utils

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest

class CoilUtil {
    companion object {
        /**
         * Loads an SVG image from a URL into an ImageView using Coil with a ProgressBar.
         *
         * @param context The context to use for loading the image.
         * @param imageView The ImageView to load the image into.
         * @param url The URL of the SVG image to load.
         * @param progressBar The ProgressBar to show while loading the image.
         * @param placeholderResId (Optional) Resource ID for the placeholder image.
         * @param errorResId (Optional) Resource ID for the error image.
         */
        fun loadImageWithProgress(
            context: Context,
            imageView: ImageView,
            url: String,
            progressBar: ProgressBar,
            placeholderResId: Int? = null,
            errorResId: Int? = null
        ) {
            // Show progress bar before loading
            progressBar.visibility = View.VISIBLE

            // Create a custom ImageLoader with SvgDecoder
            val imageLoader = ImageLoader.Builder(context)
                .components { add(SvgDecoder.Factory()) }
                .build()

            // Build and execute the image request
            val request = ImageRequest.Builder(context)
                .data(url)
                .target(
                    onStart = { progressBar.visibility = View.VISIBLE },
                    onSuccess = { result ->
                        imageView.setImageDrawable(result)
                        progressBar.visibility = View.GONE
                        imageView.visibility = View.VISIBLE
                    },
                    onError = {
                        progressBar.visibility = View.GONE
                        errorResId?.let { imageView.setImageResource(it) }
                    }
                )
                .apply {
                    placeholderResId?.let { placeholder(it) }
                    errorResId?.let { error(it) }
                }
                .build()

            imageLoader.enqueue(request)
        }
    }
}
