package com.app.text2me.ui.adapter

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.app.text2me.databinding.ItemChatBinding
import com.app.text2me.ui.model.ChatModel
import com.github.androtoast.hide
import com.github.androtoast.show

class ChatAdapter(var listener: OnClick) :
    RecyclerView.Adapter<ChatAdapter.ViewHolder>() {
    private var chatList = mutableListOf<ChatModel>()

    inner class ViewHolder(val binding: ItemChatBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemChatBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(chatList[position]) {
            when (msgDirection) {
                "send" -> {
                    holder.binding.llSend.show()
                    holder.binding.llRecieved.hide()
                    holder.binding.tvSendTime.text = time
                    when (msgType) {
                        "text" -> {
                            holder.binding.cvMessageSend.show()
                            holder.binding.imageSend.hide()
                            holder.binding.layoutSendImgText.hide()
                            holder.binding.textSend.text = message
                        }

                        "image" -> {
                            holder.binding.imageSend.show()
                            holder.binding.cvMessageSend.hide()
                            holder.binding.layoutSendImgText.hide()
                            holder.binding.imageSend.load(image)
                            holder.binding.imageSend.setOnClickListener {
                                if (holder.binding.imageSend.drawable == null) {
                                    Toast.makeText(it.context, "please wait", Toast.LENGTH_SHORT)
                                        .show()
                                } else {
                                    listener.openImage(holder.binding.imageSend.drawable)
                                }
                            }
                        }

                        "image_text" -> {
                            holder.binding.layoutSendImgText.show()
                            holder.binding.imageSend.hide()
                            holder.binding.cvMessageSend.hide()
                            holder.binding.tvSendMsgImgText.text = message
                            holder.binding.imgSendImgText.load(image)
                            holder.binding.imgSendImgText.setOnClickListener {
                                if (holder.binding.imgSendImgText.drawable == null) {
                                    Toast.makeText(it.context, "please wait", Toast.LENGTH_SHORT)
                                        .show()
                                } else {
                                    listener.openImage(holder.binding.imgSendImgText.drawable)
                                }
                            }
                        }

                    }
                }

                "recieved" -> {
                    holder.binding.llRecieved.show()
                    holder.binding.llSend.hide()
                    holder.binding.tvRecievedTime.text = time
                    when (msgType) {
                        "text" -> {
                            holder.binding.cvMessageRecieved.show()
                            holder.binding.imageRecieved.hide()
                            holder.binding.layoutRecievedImgText.hide()
                            holder.binding.textRecieved.text = message
                        }

                        "image" -> {
                            holder.binding.imageRecieved.show()
                            holder.binding.cvMessageRecieved.hide()
                            holder.binding.layoutRecievedImgText.hide()
                            holder.binding.imageRecieved.load(image)
                            holder.binding.imageRecieved.setOnClickListener {
                                if (holder.binding.imageRecieved.drawable == null) {
                                    Toast.makeText(it.context, "please wait", Toast.LENGTH_SHORT)
                                        .show()
                                } else {
                                    listener.openImage(holder.binding.imageRecieved.drawable)
                                }
                            }
                        }

                        "image_text" -> {
                            holder.binding.layoutRecievedImgText.show()
                            holder.binding.imageRecieved.hide()
                            holder.binding.cvMessageRecieved.hide()
                            holder.binding.textRecImgText.text = message
                            holder.binding.imgRecImgText.load(image)
                            holder.binding.imgRecImgText.setOnClickListener {
                                if (holder.binding.imgRecImgText.drawable == null) {
                                    Toast.makeText(it.context, "please wait", Toast.LENGTH_SHORT)
                                        .show()
                                } else {
                                    listener.openImage(holder.binding.imgRecImgText.drawable)
                                }
                            }
                        }

                    }
                }
            }
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: List<ChatModel>) {
        this.chatList.clear()
        this.chatList.addAll(list)
        notifyDataSetChanged()
    }

    fun updateList(pos: Int, list: List<ChatModel>) {
        this.chatList.clear()
        this.chatList.addAll(list)
        notifyItemChanged(pos)
    }

    interface OnClick {
        fun openImage(drawable: Drawable)

    }

    override fun getItemCount(): Int {
        return chatList.size
    }

}