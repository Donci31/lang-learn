package hu.bme.aut.langlearn.domain.use_cases.practice_screen

import hu.bme.aut.langlearn.domain.repositories.SentenceRepository
import javax.inject.Inject

class GetSentenceUseCase @Inject constructor(
    private val sentenceRepository: SentenceRepository,
) {

    suspend operator fun invoke(word: String, languageCode: String): String =
        sentenceRepository.getSentence(word, languageCode)
            .replace(
                Regex("\\b${Regex.escape(word)}\\b", RegexOption.IGNORE_CASE),
                "_".repeat(5)
            )
}