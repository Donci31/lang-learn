package hu.bme.aut.langlearn.domain.entities

import java.util.Date

data class Practice(
    val date: Date = Date(),
    val score: Double = 0.0
)