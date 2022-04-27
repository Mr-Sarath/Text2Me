package com.app.text2me.ui.activities

import android.Manifest
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.StrictMode
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.app.text2me.databinding.ActivityImagePreviewBinding
import com.app.text2me.utils.shortToast
import com.lyrebirdstudio.aspectratiorecyclerviewlib.aspectratio.model.AspectRatio
import com.lyrebirdstudio.croppylib.Croppy
import com.lyrebirdstudio.croppylib.main.CropRequest
import com.lyrebirdstudio.croppylib.main.StorageType
import com.permissionx.guolindev.PermissionX
import java.text.SimpleDateFormat
import java.util.*

class ImagePreviewActivity : AppCompatActivity() {
    private var binding:ActivityImagePreviewBinding?=null
    private var imgbase64: String? = null
    private val IMAGE_REQUEST_CODE = 1000
    private val RC_CROP_IMAGE = 222
    var mImageUri: Uri? = null
    private val calInst = Calendar.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityImagePreviewBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        init()
    }
    private fun init() {
        askPermissions()
        handleEvents()
    }

    private fun askPermissions() {
        PermissionX.init(this@ImagePreviewActivity)
            .permissions(
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            .onExplainRequestReason { scope, deniedList ->
                scope.showRequestReasonDialog(
                    deniedList,
                    "These permissions are required to function correctly",
                    "OK",
                    "Cancel"
                )
            }
            .onForwardToSettings { scope, deniedList ->
                scope.showForwardToSettingsDialog(
                    deniedList,
                    "You need to allow these permissions in Settings manually",
                    "Open Settings",
                    "Cancel"
                )
            }
            .request { allGranted, grantedList, deniedList ->
                if (allGranted) {
                    openCamera()
                } else {
                    shortToast("Accept all permissions and try again")
                    finish()
                }
            }
    }

    private fun handleEvents() {
        binding?.ivClose?.setOnClickListener {
            onBackPressed()
        }
        binding?.ivCrop?.setOnClickListener {
            mImageUri?.let {
                cropImg(it)
            }
        }
        binding?.ivSend?.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        val dateFormat = SimpleDateFormat("hh:mm a")
        val time = dateFormat.format(calInst.time)
        val msg = binding?.etPreviewMessage?.text.toString().trim()
        val intent = Intent()
        if (msg.isNullOrEmpty()) {
            intent.putExtra("msgType", "image")
            intent.putExtra("message", "")
        } else {
            intent.putExtra("msgType", "image_text")
            intent.putExtra("message", msg)
        }
        intent.putExtra("time", time)
        //  intent.putExtra("image", imgbase64)


        intent.putExtra("image", mImageUri.toString())
        setResult(RESULT_OK, intent)
        super.onBackPressed()
    }


    private fun cropImg(uri: Uri) {
        val cropRequest = CropRequest.Auto(
            sourceUri = uri,
            requestCode = RC_CROP_IMAGE,
            storageType = StorageType.CACHE,
            excludedAspectRatios = arrayListOf(
                AspectRatio.ASPECT_INS_1_1,
                AspectRatio.ASPECT_INS_4_5,
                AspectRatio.ASPECT_INS_STORY,
                AspectRatio.ASPECT_FACE_POST,
                AspectRatio.ASPECT_FACE_COVER,
                AspectRatio.ASPECT_PIN_POST,
                AspectRatio.ASPECT_YOU_COVER,
                AspectRatio.ASPECT_TWIT_POST,
                AspectRatio.ASPECT_TWIT_HEADER,
                AspectRatio.ASPECT_A_4,
                AspectRatio.ASPECT_A_5,
            )
        )
        Croppy.start(this@ImagePreviewActivity, cropRequest)
    }

    private fun openCamera() {
        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "HiLITE_invoice.jpg")
        values.put(
            MediaStore.Images.Media.DESCRIPTION, "Photo taken on " + System.currentTimeMillis()
        )
        mImageUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        val takePicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        takePicture.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri)
        startActivityForResult(takePicture, IMAGE_REQUEST_CODE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            IMAGE_REQUEST_CODE -> {
                if (resultCode == RESULT_OK) {
                    mImageUri?.let { uri ->
                        binding?.imagePreview?.load(uri)
                    }
                } else {
                    finish()
                }
            }

            RC_CROP_IMAGE -> {
                if (resultCode == RESULT_OK) {
                    val uri = data?.data
                    binding?.imagePreview?.load(data?.data)
                } else {
                    finish()
                }
            }
        }
    }

}