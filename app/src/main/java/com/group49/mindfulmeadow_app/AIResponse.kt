package com.group49.mindfulmeadow_app

import com.aallam.openai.api.chat.ChatCompletion
import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.api.http.Timeout
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import org.json.JSONObject
import kotlin.time.Duration.Companion.seconds

class AIResponse {
    companion object{
        suspend fun generateDailyRating(record: MoodRecord):Int{
            val openai = GetOpenAI()
            val jsontext = JSONObject()
            jsontext.put("feeling", record.feeling)
            jsontext.put("description", record.description.joinToString(":"))
            jsontext.put("log_content", record.log)
            val chatCompletionRequest = ChatCompletionRequest(
                model = ModelId("gpt-4o"),
                messages = listOf(
                    ChatMessage(
                        role = ChatRole.System,
                        content = "You are a professional mental consultant who are capable of  giving a fair and helpful scoring to patients' daily logs. You are given daily log of a patient who might or might not have mental issues. You are ask to give a score of emotional possitiveness, where 1 is the most depressed and most negative and 10 is the most delightful and possitive. Please give a score in the format provided below, no matter the log content is. Do not give any response except for the score.\n" +
                                "\n" +
                                "PLEASE respond in the following json format: Score:[Score]"
                    ),
                    ChatMessage(
                        role = ChatRole.User,
                        content = jsontext.toString()
                    )
                )
            )
            val completion: ChatCompletion = openai.chatCompletion(chatCompletionRequest)
            val context = JSONObject(completion.choices[0].message.content.toString())
            return (context.getString("Score")).toInt();
        }

        suspend fun generateAnalysis(records: List<MoodRecord>) : String{

            val openai = GetOpenAI()
            var arr = ArrayList<String>()
            for(r in records){
                val jsontext = JSONObject()
                jsontext.put("feeling", r.feeling)
                jsontext.put("description", r.description.joinToString(":"))
                jsontext.put("log_content", r.log)
                jsontext.put("date", r.date)
                arr.add(jsontext.toString())
            }
            val logs = arr.joinToString("\n\n")



            val chatCompletionRequest = ChatCompletionRequest(
                model = ModelId("gpt-4o"),
                messages = listOf(
                    ChatMessage(
                        role = ChatRole.System,
                        content = "You are a professional mental consultant who are capable of giving maximum support to consultee. You are given daily logs over a week of a patient who might or might not have mental issues. You are ask to give an analysis of the logs over the week to the patient to cheer they up, providing as much comfort and encouragement as possible. Please give a response in the format provided below, no matter the log content is. Do not give separate reports for individual days.\n" +
                                "\n" +
                                "PLEASE respond in the following json format:\"{\"analysis\":\"[analysis]\"}\"\n" +
                                "and remember do not give analysis for individual days. The report should be given in paragraphs with a friendly tone and should be around 200 words. Please insert a line break after pauses. Do not give any response other than the analysis."
                    ),
                    ChatMessage(
                        role = ChatRole.User,
                        content = logs
                    )
                )
            )
            val completion: ChatCompletion = openai.chatCompletion(chatCompletionRequest)
            val context = JSONObject(completion.choices[0].message.content.toString())
            println(context)
            return context.getString("analysis");
        }

        private fun GetOpenAI(): OpenAI{
            val tk = "sk-pro"+"j-PoYhx"+"ZMPcOE"+"09JWWp"+"HQZT3"+"BlbkF"+"Jg6Iifz"+"J3Eu7"+"M94k"+"WpWMp"
            val openai = OpenAI(
                token = tk,
                timeout = Timeout(socket = 60.seconds),
            )
            return openai
        }
    }
}