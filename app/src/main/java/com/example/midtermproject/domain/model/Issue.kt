package com.example.midtermproject.domain.model

import com.squareup.moshi.Json


data class Issue(

    val issue: IssueDetails,
    val specialisation: List<Specialisation>
)

data class IssueDetails(

    val id: Int,
    val name: String,
    val profName: String,
    val icd: String,
    val icdName: String,
    val accuracy: Double
)

data class Specialisation(
    val id: Int,
    val name: String,
    val specialistId: Int
)