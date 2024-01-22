package com.example.midtermproject.data.remote.model

import com.squareup.moshi.Json

data class MedicineResponse(
    @Json(name = "message") val message: String,
    @Json(name = "data") val data: MedicineData
)

data class MedicineData(
    @Json(name = "medicines") val medicines: List<Medicine>
)

data class Medicine(
    @Json(name = "id") val id: Long,
    @Json(name = "name") val name: String,
    @Json(name = "price") val price: Double,
    @Json(name = "content") val content: String,
    @Json(name = "companyName") val companyName: String,
    @Json(name = "rank") val rank: Double
)

