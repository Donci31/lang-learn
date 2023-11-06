package hu.bme.aut.langlearn.data.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface OpenAIAPI {

    @Headers("Content-Type: application/json")
    @POST("v1/chat/completions")
    suspend fun getChatCompletions(
        @Header("Authorization") authorization: String,
        @Body requestBody: ChatRequest
    ): Response<ChatResponse>
}