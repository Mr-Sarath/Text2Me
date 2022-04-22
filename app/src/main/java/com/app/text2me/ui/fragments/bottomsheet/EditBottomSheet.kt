package com.app.text2me.ui.fragments.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.text2me.R
import com.app.text2me.databinding.MenuBottomsheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class EditBottomSheet() : BottomSheetDialogFragment() {
    private var binding: MenuBottomsheetBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MenuBottomsheetBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
    }
//    override fun onCancel(dialog: DialogInterface) {
//        super.onCancel(dialog)
//        requireActivity().findViewById<BottomNavigationView>(R.id.bottomN).selectedItemId =
//
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}