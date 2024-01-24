package com.example.midtermproject.domain.mapper

import com.example.midtermproject.domain.model.Issue
import com.example.midtermproject.presentation.model.IssueUI
import java.text.DecimalFormat
import kotlin.math.roundToInt

fun Issue.toPresentation() = IssueUI(id = issue.id,
    name = issue.name, profName = issue.profName, accuracy = issue.accuracy.roundToInt(), specialisation = specialisation.map {
        it.name
    }
)

