package hu.bme.aut.langlearn.domain.entities

data class Deck(
    val id: String = "",
    val name: String = "",
    val private: Boolean = false,
    val hasAccess: List<String> = emptyList(),
    val languageCode: String = "",
    val words: List<Word> = emptyList()
)
