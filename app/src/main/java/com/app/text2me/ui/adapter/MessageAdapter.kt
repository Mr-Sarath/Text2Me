package com.app.text2me.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.text2me.databinding.ItemMessageBinding
import com.app.text2me.ui.model.MessageItem

class MessageAdapter(private var itemList: List<MessageItem>, private var listener: MessageListener) :
    RecyclerView.Adapter<MessageAdapter.MyViewHolder>() {
    inner class MyViewHolder(val binding: ItemMessageBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemMessageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(itemList[position]) {
            holder.binding.ivItem.setImageResource(image)
            holder.binding.tvTitle.text = names
            holder.binding.tvQualification.text = title
            holder.binding.cardview.setOnClickListener {
                listener.onItemClick(itemList[position])
            }
        }

    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    interface MessageListener {
        fun onItemClick(messageItem: MessageItem)
    }
}