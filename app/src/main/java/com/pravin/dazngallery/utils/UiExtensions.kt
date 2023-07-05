package com.pravin.dazngallery.utils

import android.content.Context
import android.widget.Toast

fun Context.showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun Context.loadJSONFromAssets(fileName: String): String {
    return this.assets.open(fileName).bufferedReader().use { reader ->
        reader.readText()
    }
}
