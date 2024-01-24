package com.example.midtermproject.data.remote.model

import com.squareup.moshi.Json


data class IssueDto(
    @Json(name = "Issue")
    val issue: IssueDetailsDto,
    @Json(name = "Specialisation")
    val specialisation: List<SpecialisationDto>
)

data class IssueDetailsDto(
    @Json(name = "ID")
    val id: Int,
    @Json(name = "Name")
    val name: String,
    @Json(name = "ProfName")
    val profName: String,
    @Json(name = "Icd")
    val icd: String,
    @Json(name = "IcdName")
    val icdName: String,
    @Json(name = "Accuracy")
    val accuracy: Double
)

data class SpecialisationDto(
    @Json(name = "ID")
    val id: Int,
    @Json(name = "Name")
    val name: String,
    @Json(name = "SpecialistID")
    val specialistId: Int
)
