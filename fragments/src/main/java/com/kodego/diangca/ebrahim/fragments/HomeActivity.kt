package com.kodego.diangca.ebrahim.fragments
/**
OLD WAY FRAGMENT STYLE
* */
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.kodego.diangca.ebrahim.fragments.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {


    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var fragmentTransaction1:FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction1.replace(R.id.fragment_holder1, FirstFragment())
        fragmentTransaction1.commit()

        var fragmentTransaction2:FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction2.replace(R.id.fragment_holder2, SecondFragment())
        fragmentTransaction2.commit()
    }
}