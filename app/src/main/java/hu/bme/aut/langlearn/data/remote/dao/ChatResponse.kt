package hu.bme.aut.langlearn.data.remote.dao

data class ChatResponse(
    val id: String,
    val `object`: String,
    val created: Long,
    val model: String,
    val choices: List<Choice>,
    val usage: TokenUsage
)

data class Choice(
    val index: Int,
    val message: Message,
    val finish_reason: String
)

data class TokenUsage(
    val prompt_tokens: Int,
    val completion_tokens: Int,
    val total_tokens: Int
)
