package com.app.text2me.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.viewpager.widget.ViewPager
import com.app.text2me.R
import com.app.text2me.databinding.ActivityMainBinding
import com.app.text2me.ui.adapter.homeviepager.ViewpagerAdapter
import com.app.text2me.ui.fragments.MessageFragment
import com.app.text2me.ui.fragments.ProfileFragment
import com.app.text2me.ui.fragments.SettingsFragment
import com.app.text2me.ui.fragments.bottomsheet.EditBottomSheet
import com.github.androtoast.AndroToast
import com.github.dhaval2404.imagepicker.ImagePicker

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private var viewPagerAdapter: ViewpagerAdapter? = null

    var messageFragment: MessageFragment? = null
    var profileFragment: ProfileFragment? = null
    var settingsFragment: SettingsFragment? = null

    var imagePicker: ImageView? = null

    companion object {
        val IMAGE_REQUEST_CODE = 1_000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        init()

    }

    private fun init() {
//        binding?.tvText?.setOnClickListener {
//  AndroToast.apply {
//                text="Hi visakh"
//                drawableLeft= R.drawable.skz
//            start(this@MainActivity)
//            }
//        }

        binding?.ivLogo?.setOnClickListener {
            imagePicker()
        }
        binding?.ivEdit?.setOnClickListener {
            EditBottomSheet().show(this@MainActivity.supportFragmentManager, "Hi")
        }

        binding?.ivMessages?.setOnClickListener {
            setMessage()
        }

        binding?.ivProfile?.setOnClickListener {
            setProfile()

        }

        binding?.ivSettings?.setOnClickListener {
            setSettings()

        }

        binding?.vp?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        setMessage()
                    }
                    1 -> {
                        setProfile()
                    }
                    2 -> {
                        setSettings()
                    }

                }
            }
        })

        setViewPager()
    }
    private fun setViewPager() {
        val viewPager = ViewpagerAdapter(supportFragmentManager)

        val messageFragment = MessageFragment()

        profileFragment = ProfileFragment()

        settingsFragment = SettingsFragment()

        viewPager.addFragment(messageFragment)

        profileFragment?.let {
            viewPager.addFragment(it)
        }

        settingsFragment?.let {
            viewPager.addFragment(it)
        }
        binding?.vp?.adapter = viewPager
    }


    private fun setMessage() {
        binding?.viewMessage?.visibility= View.VISIBLE
        binding?.viewProfile?.visibility= View.GONE
        binding?.viewSettings?.visibility= View.GONE

        binding?.vp?.currentItem = 0
    }

    private fun setProfile() {
        binding?.viewMessage?.visibility= View.GONE
        binding?.viewProfile?.visibility= View.VISIBLE
        binding?.viewSettings?.visibility= View.GONE

        binding?.vp?.currentItem = 1

    }

    private fun setSettings() {
        binding?.viewMessage?.visibility= View.GONE
        binding?.viewProfile?.visibility= View.GONE
        binding?.viewSettings?.visibility= View.VISIBLE
        binding?.vp?.currentItem = 2

    }

    private fun imagePicker() {
        ImagePicker.with(this)
            .crop() //Crop image(Optional), Check Customization for more option
            .compress(1024)            //Final image size will be less than 1 MB(Optional)
            .maxResultSize(
                1080,
                1080
            )    //Final image resolution will be less than 1080 x 1080(Optional)
            .start(IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            binding?.ivLogo?.setImageURI(data?.data)
        }
    }
}