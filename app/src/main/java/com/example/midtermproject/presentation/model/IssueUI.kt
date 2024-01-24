package com.example.midtermproject.presentation.model

data class IssueUI(
    var id: Int,
    var name: String,
    var profName: String,
    var accuracy: Int,
    var specialisation: List<String>
)
