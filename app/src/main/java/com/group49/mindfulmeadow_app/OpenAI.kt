package com.group49.mindfulmeadow_app

import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.api.http.Timeout
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import com.github.mikephil.charting.BuildConfig
import kotlin.time.Duration.Companion.seconds

class OpenAI {
    companion object{
        suspend fun generateDailyRating(record: MoodRecord):Int{


            return 1
        }

        suspend fun generateWeeklyAnalysis(records: List<MoodRecord>) : String{

            val tk = "sk-pro"+"j-PoYhx"+"ZMPcOE"+"09JWWp"+"HQZT3"+"BlbkF"+"Jg6Iifz"+"J3Eu7"+"M94k"+"WpWMp"

            val openai = OpenAI(
                token = tk,
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