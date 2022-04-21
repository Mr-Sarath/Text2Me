package com.app.text2me.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.app.text2me.R
import com.app.text2me.databinding.ActivityOnBoardingBinding
import com.app.text2me.model.ViewPagerItem
import com.app.text2me.ui.adapter.OnBoardingAdapter

class OnBoardingActivity : AppCompatActivity() {
    private var binding:ActivityOnBoardingBinding?=null
    private var screenPager: ViewPager? = null
    var onBoardingAdapter:OnBoardingAdapter?= null
    private var slidingDotsCount = 0
    private lateinit var slidingImageDots: Array<ImageView?>
    private val mList: MutableList<ViewPagerItem> = ArrayList<ViewPagerItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        screenPager = findViewById(R.id.vp)
        setList()
        setAdapter()

        click()

    }
    private fun setList() {

        mList.add(
            ViewPagerItem(
                "Make Me More Happier",
                "One good conversation can shift the direction of change forever",
                R.drawable.chat
            )
        )
        mList.add(
            ViewPagerItem(
                "Keep Secure Your Chat",
                "Make me Happy",
                R.drawable.chatone
            )
        )
        mList.add(
            ViewPagerItem(
                "Let's Go...",
                "Lorem ipsum dolor sit\namet,consectetur\nadipiscing elit.Tortor id\nsemper mollise",
                R.drawable.hacker
            )
        )
    }

    private fun setAdapter() {
        onBoardingAdapter = OnBoardingAdapter(this, mList)
        binding?.vp?.adapter = onBoardingAdapter


        slidingDotsCount = mList.size
        slidingImageDots = emptyArray()
        slidingImageDots = arrayOfNulls(slidingDotsCount)
        binding?.llDots?.removeAllViews()
        for (i in 0 until slidingDotsCount) {
            slidingImageDots[i] = ImageView(this@OnBoardingActivity)
            slidingImageDots[i]?.setImageDrawable(
                ContextCompat.getDrawable(
                    this@OnBoardingActivity,
                    R.drawable.non_active_dot
                )
            )
            val params =
                LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )

            params.setMargins(8, 0, 8, 0)
            binding?.llDots?.addView(slidingImageDots[i], params)
        }

        slidingImageDots[0]?.setImageDrawable(
            ContextCompat.getDrawable(
                this@OnBoardingActivity,
                R.drawable.active_dot
            )
        )

        binding?.vp?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                when (position) {
                    0 -> {
                        binding?.clNext?.visibility = View.VISIBLE
                        binding?.clBack?.visibility = View.INVISIBLE
                        binding?.clLogin?.visibility = View.INVISIBLE
                        binding?.tvWelcome?.visibility = View.GONE
                    }
                    1 -> {
                        binding?.clNext?.visibility = View.INVISIBLE
                        binding?.clBack?.visibility = View.VISIBLE
                        binding?.clLogin?.visibility = View.INVISIBLE
                        binding?.tvWelcome?.visibility = View.GONE
                    }
                    2 -> {
                        binding?.clNext?.visibility = View.INVISIBLE
                        binding?.clBack?.visibility = View.INVISIBLE
                        binding?.clLogin?.visibility = View.VISIBLE
                        binding?.tvWelcome?.visibility = View.VISIBLE

                    }
                }

            }

            override fun onPageSelected(position: Int) {
                for (i in 0 until slidingDotsCount) {
                    slidingImageDots[i]?.setImageDrawable(
                        ContextCompat.getDrawable(
                            this@OnBoardingActivity,
                            R.drawable.non_active_dot
                        )
                    )
                }

                slidingImageDots[position]?.setImageDrawable(
                    ContextCompat.getDrawable(
                        this@OnBoardingActivity,
                        R.drawable.active_dot
                    )
                )
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

        })
    }


    private fun savePrefsData() {
        val preferences = applicationContext.getSharedPreferences("myPrefs", MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putBoolean("isIntroOpened", true)
        editor.apply()
    }

    private fun loadLastScreen() {
//        linearLayoutNext.setVisibility(View.INVISIBLE)
//        linearLayoutGetStarted.setVisibility(View.VISIBLE)
    }

    private fun click() {
        binding?.btnLogin?.setOnClickListener {
            val i = Intent(this@OnBoardingActivity, LoginActivity::class.java)
            startActivity(i)
//            finish()
        }
        binding?.btnSkip?.setOnClickListener {
            val i = Intent(this@OnBoardingActivity, LoginActivity::class.java)
            startActivity(i)
//            finish()
        }
//        binding?.btnBack?.setOnClickListener {
//         onBackPressed()
////            finish()
//        }
        binding?.btnSign?.setOnClickListener {
            val i = Intent(this@OnBoardingActivity, SignUpActivity::class.java)
            startActivity(i)
        }
    }
}