package hu.bme.aut.langlearn.data.repositories

import hu.bme.aut.langlearn.BuildConfig
import hu.bme.aut.langlearn.data.remote.ChatRequestBody
import hu.bme.aut.langlearn.data.remote.Message
import hu.bme.aut.langlearn.data.remote.OpenAIAPI
import javax.inject.Inject

class SentenceRepositoryImpl @Inject constructor(
    private val openAIAPI: OpenAIAPI
): SentenceRepository {

    override suspend fun getSentence(word: String): String {
        val response = openAIAPI.getChatCompletions(
            authorization = "Bearer ${BuildConfig.OPENAI_API_KEY}",
            requestBody = ChatRequestBody(
                model = "gpt-3.5-turbo",
                messages = listOf(
                    Message(role = "system", content = "Make a sentence with the word."),
                    Message(role = "user", content = word)
                ),
                temperature = 1.0,
                max_tokens = 30,
                top_p = 1.0,
                frequency_penalty = 0.0,
                presence_penalty = 0.0
            )
        )
        return response.body()?.choices?.first()?.message?.content ?: ""
    }
}