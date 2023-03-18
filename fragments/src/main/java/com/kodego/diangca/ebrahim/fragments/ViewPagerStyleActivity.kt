package com.kodego.diangca.ebrahim.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.RotateAnimation
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.kodego.diangca.ebrahim.fragments.adapter.FragmentAdapter
import com.kodego.diangca.ebrahim.fragments.classes.ZoomOutPageTransformer
import com.kodego.diangca.ebrahim.fragments.databinding.ActivityViewPagerBinding
import com.kodego.diangca.ebrahim.fragments.databinding.ActivityViewStylePagerBinding

class ViewPagerStyleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewStylePagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewStylePagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var fragmentAdapter = FragmentAdapter(supportFragmentManager, lifecycle)
        fragmentAdapter.addFragment(AFragment())
        fragmentAdapter.addFragment(BFragment())
        fragmentAdapter.addFragment(CFragment())
        fragmentAdapter.addFragment(ChatFragment())
        fragmentAdapter.addFragment(AccountFragment())

        with(binding.viewPager2) {
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
//            setPageTransformer(ZoomOutPageTransformer())
            adapter = fragmentAdapter
        }

        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
//            tab.text = "Chapter ${(position + 1)}"

            Log.d("ViewPage", "Position : ${tab.position}")
            /*when(tab.position){
                1 -> tab.setIcon(R.drawable.home).text = "HOME"
                2 -> tab.setIcon(R.drawable.order).text = "HOME"
                3 -> tab.setIcon(R.drawable.announcement).text = "HOME"
                4 -> tab.setIcon(R.drawable.chat).text = "HOME"
                4 -> tab.setIcon(R.drawable.user).text = "HOME"
            }*/
        }.attach()

        with(binding.tabLayout) {
            getTabAt(0)!!.setIcon(R.drawable.home1).text = "HOME"
            getTabAt(1)!!.setIcon(R.drawable.order1).text = "ORDERS"
            getTabAt(2)!!.setIcon(R.drawable.announcement1).text = "NEWS"
            getTabAt(3)!!.setIcon(R.drawable.chat1).text = "INBOX"
            getTabAt(4)!!.setIcon(R.drawable.user1).text = "ACCOUNT"
        }



    }

    override fun onBackPressed() {
        if (binding.viewPager2.currentItem==0) {
            super.onBackPressed()
        } else {
            binding.viewPager2.currentItem = binding.viewPager2.currentItem - 1
        }
    }
}