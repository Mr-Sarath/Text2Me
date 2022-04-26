package com.app.text2me.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.Window
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.DrawableRes
import coil.load
import coil.loadAny
import com.app.text2me.R
import okhttp3.HttpUrl
import java.io.File

fun Context.showImage(url: String) {
    val dialog = Dialog(this)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.setCancelable(true)
    dialog.setCanceledOnTouchOutside(true)
    dialog.setContentView(R.layout.dialog_image_view)
    dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    val imgView = dialog.findViewById<ImageView>(R.id.profilePic)
    if (url.isNotEmpty()) {
        imgView.load(url)
    } else {
        Toast.makeText(this, "Error. Try again", Toast.LENGTH_SHORT).show()
        dialog.dismiss()
    }
    dialog.show()
}

fun Context.showImage(httpUrl: HttpUrl) {
    val dialog = Dialog(this)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.setCancelable(true)
    dialog.setCanceledOnTouchOutside(true)
    dialog.setContentView(R.layout.dialog_image_view)
    dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    val imgView = dialog.findViewById<ImageView>(R.id.profilePic)
    imgView.load(httpUrl)
    dialog.show()
}

fun Context.showImage(uri: Uri) {
    val dialog = Dialog(this)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.setCancelable(true)
    dialog.setCanceledOnTouchOutside(true)
    dialog.setContentView(R.layout.dialog_image_view)
    dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    val imgView = dialog.findViewById<ImageView>(R.id.profilePic)
    imgView.load(uri)
    dialog.show()
}

fun Context.showImage(file: File) {
    val dialog = Dialog(this)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.setCancelable(true)
    dialog.setCanceledOnTouchOutside(true)
    dialog.setContentView(R.layout.dialog_image_view)
    dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    val imgView = dialog.findViewById<ImageView>(R.id.profilePic)
    imgView.load(file)
    dialog.show()
}

fun Context.showImage(@DrawableRes drawable: Int) {
    val dialog = Dialog(this)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.setCancelable(true)
    dialog.setCanceledOnTouchOutside(true)
    dialog.setContentView(R.layout.dialog_image_view)
    dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    val imgView = dialog.findViewById<ImageView>(R.id.profilePic)
    imgView.load(drawable)
    dialog.show()
}

fun Context.showImage(drawable: Drawable) {
    val dialog = Dialog(this)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.setCancelable(true)
    dialog.setCanceledOnTouchOutside(true)
    dialog.setContentView(R.layout.dialog_image_view)
    dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    val imgView = dialog.findViewById<ImageView>(R.id.profilePic)
    imgView.load(drawable)
    dialog.show()
}

fun Context.showImage(bitmap: Bitmap) {
    val dialog = Dialog(this)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.setCancelable(true)
    dialog.setCanceledOnTouchOutside(true)
    dialog.setContentView(R.layout.dialog_image_view)
    dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    val imgView = dialog.findViewById<ImageView>(R.id.profilePic)
    imgView.load(bitmap)
    dialog.show()
}

fun Context.showImage(any: Any) {
    val dialog = Dialog(this)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.setCancelable(true)
    dialog.setCanceledOnTouchOutside(true)
    dialog.setContentView(R.layout.dialog_image_view)
    dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    val imgView = dialog.findViewById<ImageView>(R.id.profilePic)
    imgView.loadAny(any)
    dialog.show()
}
