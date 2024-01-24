package com.example.midtermproject.data.remote.model

import com.squareup.moshi.Json

data class TokenResponseDto(
    @Json(name = "Token")
    var token: String,
    @Json(name = "ValidThrough")
    var validThrough: Long
)
