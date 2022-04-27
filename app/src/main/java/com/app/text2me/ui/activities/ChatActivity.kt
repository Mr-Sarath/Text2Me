package com.app.text2me.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.text2me.R
import com.app.text2me.databinding.ActivityChatBinding
import com.app.text2me.ui.adapter.ChatAdapter
import com.app.text2me.ui.model.ChatModel
import com.app.text2me.utils.shortToast
import com.app.text2me.utils.showImage
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*

class ChatActivity : AppCompatActivity() {
    private var binding: ActivityChatBinding? = null
    private val IMAGE_REQ_CODE = 123
    private var chatList = mutableListOf<ChatModel>()
    private val calInst = Calendar.getInstance()
    private var chatAdapter: ChatAdapter? = null



    private val database = Firebase.database
    val root = database.reference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        init()
    }
    private fun init() {
        setRecycler()
        getRecyclerData()
        handleEvents()
    }

    @SuppressLint("SimpleDateFormat")
    private fun handleEvents() {

        binding?.ivCamera?.setOnClickListener {
            shortToast("hi")
            root.child("message").setValue("Hello, World!")

            /* Intent(this@ChatActivity, ImagePreviewActivity::class.java).also {
                 startActivityForResult(it, IMAGE_REQ_CODE)
             }*/
        }
        binding?.ivSend?.setOnClickListener {
            val dateFormat = SimpleDateFormat("hh:mm a")
            val time = dateFormat.format(calInst.time)
            val msg = binding?.etMessage?.text.toString().trim()
            if (msg.isNotEmpty()) {
                sendMessage(msg, "text", "", time)
            }
        }
        binding?.inclToolbar?.ivBack?.setOnClickListener {
            onBackPressed()
        }
    }

    private fun sendMessage(msg: String, msgType: String, image: String, time: String) {
        chatList.add(
            ChatModel(
                message = msg,
                msgType = msgType,
                image = image,
                msgDirection = "send",
                time = time
            )
        )
        chatAdapter?.updateList(chatList.size, chatList)
        binding?.recycleV?.smoothScrollToPosition(chatList.size)
        binding?.etMessage?.text?.clear()
    }


    private fun getRecyclerData() {

        chatList.clear()
        chatList.add(ChatModel("Hi...", "text", "", "send", "02:02 PM"))
        chatList.add(ChatModel("Hello", "text", "", "recieved", "02:03 PM"))
        chatList.add(ChatModel("n", "image", "https://picsum.photos/500", "send", "02:03 PM"))
        chatList.add(ChatModel("Hiii", "text", "sdf", "recieved", "02:04 PM"))
        chatList.add(ChatModel("Helloooo", "text", "", "send", "02:05 PM"))
        chatList.add(
            ChatModel(
                "Nallww",
                "image",
                "https://picsum.photos/500",
                "recieved",
                "03:00 PM"
            )
        )
        chatList.add(
            ChatModel(
                "Test with Image and Text[Recieved]",
                "image_text",
                "https://picsum.photos/500",
                "recieved",
                "03:10 PM"
            )
        )
        chatList.add(
            ChatModel(
                "Test with Image and Text[Send]",
                "image_text",
                "https://picsum.photos/500",
                "send",
                "03:15 PM"
            )
        )
        chatAdapter?.updateList(chatList)

        binding?.recycleV?.smoothScrollToPosition(chatList.size - 1)
    }

    private fun setRecycler() {
        chatAdapter = ChatAdapter(object : ChatAdapter.OnClick {
            override fun openImage(drawable: Drawable) {
                this@ChatActivity.showImage(drawable)
            }
        })
        binding?.recycleV?.apply {
            layoutManager = LinearLayoutManager(this@ChatActivity)
            adapter = chatAdapter
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            IMAGE_REQ_CODE -> {
                if (resultCode == RESULT_OK) {
                    val msgType = data?.getStringExtra("msgType").toString()
                    val msg = data?.getStringExtra("message").toString()
                    val time = data?.getStringExtra("time").toString()
                    val image = data?.getStringExtra("image").toString()
                    sendMessage(msg, msgType, image, time)
                }
            }
        }
    }


}