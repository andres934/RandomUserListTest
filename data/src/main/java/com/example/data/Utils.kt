package com.example.data

import java.text.SimpleDateFormat
import java.util.Locale

internal fun String.getFormattedDateString(): String =
    runCatching {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
        val outputFormat = SimpleDateFormat("dd-MM-yyyy", Locale.US)

        inputFormat.parse(this)?.run {
            outputFormat.format(this)
        } ?: this@getFormattedDateString
    }.getOrElse {
        this
    }