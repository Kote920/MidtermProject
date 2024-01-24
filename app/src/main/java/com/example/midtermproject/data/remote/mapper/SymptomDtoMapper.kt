package com.example.midtermproject.data.remote.mapper

import com.example.midtermproject.data.remote.model.SymptomDto
import com.example.midtermproject.domain.model.Symptom

fun SymptomDto.toDomain() = Symptom(id = id, name = name)

