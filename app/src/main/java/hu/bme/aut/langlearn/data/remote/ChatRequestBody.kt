package hu.bme.aut.langlearn.data.remote

data class ChatRequestBody(
    val model: String,
    val messages: List<Message>,
    val temperature: Double,
    val max_tokens: Int,
    val top_p: Double,
    val frequency_penalty: Double,
    val presence_penalty: Double
)

data class Message(
    val role: String,
    val content: String
)
