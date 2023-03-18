package com.kodego.diangca.ebrahim.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.RotateAnimation
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.kodego.diangca.ebrahim.fragments.adapter.FragmentAdapter
import com.kodego.diangca.ebrahim.fragments.classes.ZoomOutPageTransformer
import com.kodego.diangca.ebrahim.fragments.databinding.ActivityViewPagerBinding

class ViewPagerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewPagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var fragmentAdapter = FragmentAdapter(supportFragmentManager, lifecycle)
        fragmentAdapter.addFragment(AFragment())
        fragmentAdapter.addFragment(BFragment())
        fragmentAdapter.addFragment(CFragment())

        with(binding.viewPager2) {
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
//            setPageTransformer(ZoomOutPageTransformer())
            adapter = fragmentAdapter
        }

        TabLayoutMediator(binding.tabLayout, binding.viewPager2){
                tab, position ->
            tab.text = "Chapter ${(position + 1)}"
        }.attach()

    }

    override fun onBackPressed() {
        if (binding.viewPager2.currentItem==0) {
            super.onBackPressed()
        } else {
            binding.viewPager2.currentItem = binding.viewPager2.currentItem - 1
        }
    }
}