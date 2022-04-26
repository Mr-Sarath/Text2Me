package com.app.text2me.ui.model

class ChatModel (
    val message: String,
    val msgType: String, // image, text or image_text
    val image: String,
    val msgDirection: String, // send or recieved
    val time: String
)