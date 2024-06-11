package com.group49.mindfulmeadow_app

import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.api.http.Timeout
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import kotlin.time.Duration.Companion.seconds

class OpenAI {
    companion object{
        suspend fun generateDailyRating(record: MoodRecord):Int{


            return 1
        }

        suspend fun generateWeeklyAnalysis(records: List<MoodRecord>) : String{
            val openai = OpenAI(
                token = "sk-proj-4S1xYlalKHc0wO3K8mJxT3BlbkFJ01cDhcZdItKmvFmaCLqK",
                timeout = Timeout(socket = 60.seconds),
            )
            val chatCompletionRequest = ChatCompletionRequest(
                model = ModelId("gpt-3.5-turbo"),
                messages = listOf(
                    ChatMessage(
                        role = ChatRole.System,
                        content = "You are a helpful assistant!"
                    ),
                    ChatMessage(
                        role = ChatRole.User,
                        content = "Hello!"
                    )
                )
            )
            return chatCompletionRequest.messages[0].content.toString();
        }
    }
}