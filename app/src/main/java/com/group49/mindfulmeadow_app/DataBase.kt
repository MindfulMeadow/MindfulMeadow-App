package com.group49.mindfulmeadow_app

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.Filter
import com.google.firebase.firestore.firestore
import java.security.MessageDigest
import java.security.spec.MGF1ParameterSpec.SHA256


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

        fun registerUser(userName: String, userPassword: String, resultCallBack: (Boolean) -> Unit){

            val passwordDigest = sha256(userPassword);
            val db = Firebase.firestore

            val map = hashMapOf (
                "username" to userName,
                "password_digest" to passwordDigest
                )

            db.collection("users")
                .add(map)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "User DocumentSnapshot added with ID: ${documentReference.id}")
                    resultCallBack(true)
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding user", e)
                    resultCallBack(false)
                }
        }

        fun verifyUserCredentials(userName: String, userPassword: String, resultCallBack: (Boolean) -> Unit){

            val db = Firebase.firestore

            db.collection("users").where(Filter.equalTo("username", userName))
                .get()
                .addOnSuccessListener { result ->
                    resultCallBack(result.documents.map { doc -> doc.data }.any { data ->
                        data?.get("password_digest")?.equals(sha256(userPassword)) == true
                    })
                }
                .addOnFailureListener { exception ->
                    Log.w(TAG, "Error getting user credentials.", exception)
                    resultCallBack(false)
                }
        }



        private fun sha256(base: String): String {
            try {
                val digest = MessageDigest.getInstance("SHA-256")
                val hash = digest.digest(base.toByteArray(charset("UTF-8")))
                val hexString = StringBuilder()
                for (i in hash.indices) {
                    val hex = Integer.toHexString(0xff and hash[i].toInt())
                    if (hex.length == 1) hexString.append('0')
                    hexString.append(hex)
                }
                return hexString.toString()
            } catch (ex: Exception) {
                throw RuntimeException(ex)
            }
        }
    }

}