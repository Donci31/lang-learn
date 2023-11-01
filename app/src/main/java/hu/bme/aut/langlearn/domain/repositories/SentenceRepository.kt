package hu.bme.aut.langlearn.domain.repositories

interface SentenceRepository {

    suspend fun getSentence(word: String, language: String): String
}