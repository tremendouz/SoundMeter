package com.example.dawid.soundmeter.utils

import android.content.Context
import android.widget.Toast

/**
 * Created by daza on 19.03.18.
 */
fun Context.toast(message: CharSequence) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

val LOCATION_REQUEST_INTERVAL: Long = 5000
val LOCATION_REQUEST_FASTEST_INTERVAL: Long = 2000

