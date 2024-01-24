package com.example.midtermproject.data.remote.mapper

import com.example.midtermproject.data.remote.model.IssueDto
import com.example.midtermproject.data.remote.model.SpecialisationDto
import com.example.midtermproject.domain.model.Issue
import com.example.midtermproject.domain.model.IssueDetails
import com.example.midtermproject.domain.model.Specialisation

fun IssueDto.toDomain() = Issue(issue = IssueDetails(
    id = issue.id,
    name = issue.name,
    profName = issue.profName,
    icd = issue.icd,
    icdName = issue.icdName,
    accuracy = issue.accuracy
), specialisation = specialisation.map { it.toDomain() })


fun SpecialisationDto.toDomain() = Specialisation(
    id = id,
    name = name,
    specialistId = specialistId
)