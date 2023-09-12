package hu.bme.aut.langlearn.domain

data class Deck(
    val name: String = "",
    val words: List<Word> = emptyList()
)
