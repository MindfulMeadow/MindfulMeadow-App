package com.group49.mindfulmeadow_app

import android.os.Bundle
import android.view.LayoutInflater
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.viewpager.widget.ViewPager
import com.group49.mindfulmeadow_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mViewPager: ViewPager
    private lateinit var mRadioGroup: RadioGroup
    private lateinit var tab1: RadioButton
    private lateinit var tab2: RadioButton
    private lateinit var tab3: RadioButton
    private lateinit var tab4: RadioButton
    private lateinit var mViews: MutableList<View>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

        mRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rb_home -> mViewPager.currentItem = 0
                R.id.rb_log_hist -> mViewPager.currentItem = 1
                R.id.rb_log_graph -> mViewPager.currentItem = 2
                R.id.rb_ai -> mViewPager.currentItem = 3
            }
        }
    }

    private fun initView() {
        mViewPager = findViewById(R.id.viewpager)
        mRadioGroup = findViewById(R.id.rg_tab)
        tab1 = findViewById(R.id.rb_home)
        tab2 = findViewById(R.id.rb_log_hist)
        tab3 = findViewById(R.id.rb_log_graph)
        tab4 = findViewById(R.id.rb_ai)

        mViews = mutableListOf(
            LayoutInflater.from(this).inflate(R.layout.home_page, null),
            LayoutInflater.from(this).inflate(R.layout.log_history, null),
            LayoutInflater.from(this).inflate(R.layout.log_graph, null),
            LayoutInflater.from(this).inflate(R.layout.ai_analysis, null)
        )

        mViewPager.adapter = MyViewPagerAdapter()

        mViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        tab1.isChecked = true
                        tab2.isChecked = false
                        tab3.isChecked = false
                        tab4.isChecked = false
                    }
                    1 -> {
                        tab1.isChecked = false
                        tab2.isChecked = true
                        tab3.isChecked = false
                        tab4.isChecked = false
                    }
                    2 -> {
                        tab1.isChecked = false
                        tab2.isChecked = false
                        tab3.isChecked = true
                        tab4.isChecked = false
                    }
                    3 -> {
                        tab1.isChecked = false
                        tab2.isChecked = false
                        tab3.isChecked = false
                        tab4.isChecked = true
                    }
                }
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }


    private inner class MyViewPagerAdapter : androidx.viewpager.widget.PagerAdapter() {
        override fun getCount(): Int = mViews.size

        override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(mViews[position])
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            container.addView(mViews[position])
            return mViews[position]
        }
    }
}