package com.group49.mindfulmeadow_app.Logging_Process

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.group49.mindfulmeadow_app.DataBase.Companion.uploadFile
import com.group49.mindfulmeadow_app.R
import java.io.File

class Logging_fourthActivity : AppCompatActivity() {

    private lateinit var mBtnNext: Button
    private lateinit var mBtnBackToThird: ImageView
    private lateinit var editText: EditText
    private lateinit var mBtnUploadImage: Button
    private lateinit var mIvUploadedImage: ImageView

    private var imageUrl: String? = null
    private val PICK_IMAGE_REQUEST = 71

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logging_fourth)

        val selectedMood = intent.getStringExtra("selectedMood")
        val selectedItems = intent.getStringArrayListExtra("selectedItems")

        mBtnNext = findViewById(R.id.btn_event_next)
        mBtnBackToThird = findViewById(R.id.iv_back_to_third)
        editText = findViewById(R.id.et_1)
        mBtnUploadImage = findViewById(R.id.btn_upload_image)
        mIvUploadedImage = findViewById(R.id.iv_uploaded_image)

        mBtnBackToThird.setOnClickListener {
            val intent = Intent(this@Logging_fourthActivity, Logging_thirdActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim)
        }

        mBtnUploadImage.setOnClickListener {
            chooseImage()
        }

        mBtnNext.setOnClickListener {
            val elaborationText = editText.text.toString()
            val intent = Intent(this@Logging_fourthActivity, Logging_fifthActivity::class.java).apply {
                putExtra("elaborationText", elaborationText)
                putExtra("selectedMood", selectedMood)
                putStringArrayListExtra("selectedItems", selectedItems)
                putExtra("imageUrl", imageUrl)
            }
            startActivity(intent)
            overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim)
        }
    }

    private fun chooseImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            val filePath = data.data
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filePath)
                mIvUploadedImage.setImageBitmap(bitmap)
                mIvUploadedImage.visibility = ImageView.VISIBLE

                val file = File(getRealPathFromURI(filePath))
                uploadFile(file, { url ->
                    imageUrl = url
                    Toast.makeText(this, "Image uploaded successfully", Toast.LENGTH_SHORT).show()
                }, {
                    Toast.makeText(this, "Image upload failed", Toast.LENGTH_SHORT).show()
                })
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun getRealPathFromURI(contentUri: Uri?): String {
        var result: String? = null
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = contentResolver.query(contentUri!!, proj, null, null, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                val column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                result = cursor.getString(column_index)
            }
            cursor.close()
        }
        return result ?: "Not found"
    }
}
