package hu.bme.aut.langlearn.data.deck_screen

data class Deck(
    val name: String,
    val words: List<Word>
) {
    constructor() : this("", emptyList())
}
