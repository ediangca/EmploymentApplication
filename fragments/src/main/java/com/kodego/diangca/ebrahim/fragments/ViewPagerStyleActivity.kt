package com.kodego.diangca.ebrahim.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.kodego.diangca.ebrahim.fragments.adapter.FragmentAdapter
import com.kodego.diangca.ebrahim.fragments.databinding.ActivityViewStylePagerBinding

class ViewPagerStyleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewStylePagerBinding
    private lateinit var mainFrame: FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewStylePagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initComponent()

    }

    private fun initComponent() {

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

//            Log.d("ViewPage", "Position : ${tab.position}")
            /*when(tab.position){
                1 -> tab.setIcon(R.drawable.home).text = "HOME"
                2 -> tab.setIcon(R.drawable.order).text = "HOME"
                3 -> tab.setIcon(R.drawable.announcement).text = "HOME"
                4 -> tab.setIcon(R.drawable.chat).text = "HOME"
                4 -> tab.setIcon(R.drawable.user).text = "HOME"
            }*/
        }.attach()
        with(binding.tabLayout) {
            getTabAt(0)!!.setIcon(R.drawable.nav_home).text = "HOME"
            getTabAt(1)!!.setIcon(R.drawable.nav_order).text = "ORDERS"
            getTabAt(2)!!.setIcon(R.drawable.nav_notify).text = "NEWS"
            getTabAt(3)!!.setIcon(R.drawable.nav_chat).text = "INBOX"
            getTabAt(4)!!.setIcon(R.drawable.nav_account).text = "ACCOUNT"
        }

        mainFrame = supportFragmentManager.beginTransaction()
        mainFrame.replace(R.id.mainFrameLayout, AFragment())
        mainFrame.commit()


        binding.navMenu.setOnItemSelectedListener {
            navMenuOnItemSelectedListener(it)
        }
    }

    private fun navMenuOnItemSelectedListener(it: MenuItem): Boolean {
        Log.d("MENU ITEM", "ID: ${it.itemId}")
        when (it.itemId) {
            R.id.nav_home -> {
                openFragment(AFragment())
//                    binding.viewPager2.currentItem = 0
                return true
            }
            R.id.nav_order -> {
                openFragment(BFragment())
//                    binding.viewPager2.currentItem = 1
                return true
            }
            R.id.nav_announcement -> {
                openFragment(CFragment())
//                    binding.viewPager2.currentItem = 2
                return true
            }
            R.id.nav_chat -> {
                openFragment(ChatFragment())
//                    binding.viewPager2.currentItem = 3
                return true
            }
            R.id.nav_account -> {
                openFragment(AccountFragment())
//                    binding.viewPager2.currentItem = 4
                return true
            }

        }
        return false
    }

    private fun openFragment(fragment: Fragment) {
        mainFrame = supportFragmentManager.beginTransaction()
        mainFrame.replace(R.id.mainFrameLayout, fragment);
        mainFrame.addToBackStack(null);
        mainFrame.commit();
    }


    override fun onBackPressed() {
        if (binding.viewPager2.currentItem==0) {
            super.onBackPressed()
        } else {
            binding.viewPager2.currentItem = binding.viewPager2.currentItem - 1
        }
    }
}