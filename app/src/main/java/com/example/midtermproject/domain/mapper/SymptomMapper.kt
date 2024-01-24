package com.example.midtermproject.domain.mapper

import com.example.midtermproject.domain.model.Symptom
import com.example.midtermproject.presentation.mapper.SymptomUI


fun Symptom.toPresentation() = SymptomUI(id = id, name = name)