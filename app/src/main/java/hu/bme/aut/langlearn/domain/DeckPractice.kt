package hu.bme.aut.langlearn.domain

data class DeckPractice(
    val deckId: String = "",
    val practices: List<Practice> = emptyList()
)
