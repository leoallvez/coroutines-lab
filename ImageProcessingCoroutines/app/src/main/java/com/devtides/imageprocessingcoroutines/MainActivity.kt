package com.devtides.imageprocessingcoroutines

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.net.URL

class MainActivity : AppCompatActivity() {

    private val coroutineScope by lazy {CoroutineScope(Dispatchers.Main)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        coroutineScope.launch {
            val originalDeferred = coroutineScope.async(Dispatchers.IO) { getOriginalBitmap() }
            val originalBitmap = originalDeferred.await()
            val alteredDeferred = coroutineScope.async (Dispatchers.Default){ applyFilter(originalBitmap) }
            val alteredBitmap = alteredDeferred.await()
            loadImage(alteredBitmap)
        }
    }

    private fun applyFilter(original: Bitmap) = Filter.apply(original)

    private fun getOriginalBitmap() = URL(IMAGE_URL).openStream().use {
        BitmapFactory.decodeStream(it)
    }

    private fun loadImage(bitmap: Bitmap) {
        progressBar.visibility = View.GONE
        imageView.setImageBitmap(bitmap)
        imageView.visibility = View.VISIBLE
    }

    companion object {
        const val IMAGE_URL =
            "https://raw.githubusercontent.com/DevTides/JetpackDogsApp/master/app/src/main/res/drawable/dog.png"
    }
}
