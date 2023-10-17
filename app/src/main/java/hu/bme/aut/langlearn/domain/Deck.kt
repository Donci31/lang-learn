package hu.bme.aut.langlearn.domain

data class Deck(
    val id: String = "",
    val name: String = "",
    val language: String = "",
    val words: List<Word> = emptyList()
)
