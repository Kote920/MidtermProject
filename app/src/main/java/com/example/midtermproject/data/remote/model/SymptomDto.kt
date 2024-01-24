package com.example.midtermproject.data.remote.model

import com.squareup.moshi.Json

data class SymptomDto(
    @Json(name = "ID")
    var id: Int,
    @Json(name = "Name")
    var name: String
)