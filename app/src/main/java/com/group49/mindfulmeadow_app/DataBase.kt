package com.group49.mindfulmeadow_app

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.group49.mindfulmeadow_app.Logging_Snd_Step_Activities.MoodRecord
import kotlinx.coroutines.delay
import java.lang.Thread.sleep
import java.util.Optional
import java.util.concurrent.Future

class DataBase {



    companion object {
        fun recordMood(moodRecord: MoodRecord, resultCallBack: (Boolean) -> Unit){
            val buffer : StringBuffer = StringBuffer()
            val serializedDescription = moodRecord.description.joinTo(buffer, ",");

            val mood_record = hashMapOf(
                "user_id" to moodRecord.userId,
                "log_id" to moodRecord.logId,
                "feeling" to moodRecord.feeling,
                "description" to buffer.toString(),
                "log_content" to moodRecord.log,
                "date" to moodRecord.date
            )

            val db = Firebase.firestore

            db.collection("mood_records")
                .add(mood_record)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                    resultCallBack(true)
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                    resultCallBack(false)
                }
        }

        fun getMoodRecordsAndConsume(userId: String, func: (List<MoodRecord>?) -> Unit) {

            val db = Firebase.firestore


            var results : ArrayList<MoodRecord> = ArrayList()

            db.collection("mood_records")
                .get()
                .addOnSuccessListener { result ->
                    result.documents.map { doc -> doc.data }.map {
                        data -> MoodRecord(
                            data?.get("user_id").toString(),
                            data?.get("log_id").toString(),
                            data?.get("feeling").toString(),
                            data?.get("description").toString().split(","),
                            data?.get("log_content").toString(),
                            data?.get("date").toString())
                    }.filter { record -> record.userId == userId }.forEach { a ->
                        results.add(a)
                    }
                    func(results.toList())

                }
                .addOnFailureListener { exception ->
                    Log.w(TAG, "Error getting documents.", exception)
                    func(null)
                }
        }
    }

}