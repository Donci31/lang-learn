package hu.bme.aut.langlearn.domain

data class DeckWithPractice(
    val id: String,
    val name: String,
    val words: List<Word>,
    val practices: List<Practice>
)