package hu.bme.aut.langlearn.domain.entities

data class DeckWithPractice(
    val id: String,
    val name: String,
    val private: Boolean,
    val hasAccess: List<String>,
    val flagEmoji: String,
    val words: List<Word>,
    val practices: List<Practice>
)