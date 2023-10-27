package hu.bme.aut.langlearn.data.repositories

interface SentenceRepository {

    suspend fun getSentence(word: String, language: String): String
}