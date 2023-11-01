package hu.bme.aut.langlearn.domain.entities

data class DeckPractice(
    val deckId: String = "",
    val practices: List<Practice> = emptyList()
)
