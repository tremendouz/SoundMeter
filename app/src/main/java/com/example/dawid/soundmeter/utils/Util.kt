package com.example.dawid.soundmeter.utils

import android.content.Context
import android.widget.Toast

/**
 * Created by daza on 19.03.18.
 */
fun Context.toast(message: CharSequence) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
