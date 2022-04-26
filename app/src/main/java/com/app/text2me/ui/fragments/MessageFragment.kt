package com.app.text2me.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.text2me.R
import com.app.text2me.databinding.FragmentMessageBinding
import com.app.text2me.ui.activities.ChatActivity
import com.app.text2me.ui.adapter.MessageAdapter
import com.app.text2me.ui.model.MessageItem

class MessageFragment : Fragment(), MessageAdapter.MessageListener {
    private var binding: FragmentMessageBinding? = null
    private var messageAdapter: MessageAdapter? = null
    var data = ArrayList<MessageItem>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMessageBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        binding?.recyclerV?.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        setData()
    }

    private fun setData() {
        data.clear()
        data.add(MessageItem(R.drawable.skz, "Ajin Krishna", "Android Developer", "TL"))
        data.add(MessageItem(R.drawable.skz, "Ajin Krishna", "Android Developer", "TL"))
        data.add(MessageItem(R.drawable.skz, "Ajin Krishna", "Android Developer", "TL"))
        data.add(MessageItem(R.drawable.skz, "Ajin Krishna", "Android Developer", "TL"))
        data.add(MessageItem(R.drawable.skz, "Ajin Krishna", "Android Developer", "TL"))

        messageAdapter = MessageAdapter(data, this)
        binding?.recyclerV?.adapter = messageAdapter
    }

    override fun onItemClick(messageItem: MessageItem) {
        val intent = Intent(requireContext(), ChatActivity::class.java)
        intent.putExtra("id", messageItem.id)
        startActivity(intent)
    }


}