package hu.bme.aut.langlearn.domain.entities

data class Deck(
    val id: String = "",
    val name: String = "",
    val languageCode: String = "",
    val words: List<Word> = emptyList()
)
