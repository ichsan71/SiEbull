package com.marqumil.siebull.ui.landingPage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.marqumil.siebull.R
import com.marqumil.siebull.databinding.ActivityLandingPageBinding
import com.marqumil.siebull.ui.ViewModelFactory
import com.marqumil.siebull.ui.askbully.AskbullyActivity
import com.marqumil.siebull.ui.hallobully.HallobullyActivity
import com.marqumil.siebull.ui.home.HomeActivity
import com.marqumil.siebull.ui.news.NewsAdapter
import com.marqumil.siebull.ui.news.NewsViewModel

class LandingPage : AppCompatActivity() {

    private lateinit var binding: ActivityLandingPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLandingPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val navView: BottomNavigationView = binding.navView
        navView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    val intent = Intent(this, LandingPage::class.java)
                    startActivity(intent)
                }
//                R.id.navigation_profile -> {
//                    val intent = Intent(this, ProfileActivity::class.java)
//                    startActivity(intent)
//                }
            }
            true
        }

        val images = listOf(R.drawable.bullying_one, R.drawable.bullying_two, R.drawable.bullying_three)
        val viewPager = findViewById<ViewPager>(R.id.viewPager)
        val adapter = ImageAdapter(this, images)
        viewPager.adapter = adapter

        binding.btnBullypedia.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        binding.btnAskbully.setOnClickListener {
            val intent = Intent(this, AskbullyActivity::class.java)
            startActivity(intent)
        }

        binding.btnHallobully.setOnClickListener {
            val intent = Intent(this, HallobullyActivity::class.java)
            startActivity(intent)
        }

        binding.toolbarBack.setOnClickListener {
            onBackPressed()
            Intent(this, HomeActivity::class.java).also {
                startActivity(it)
            }
        }

    }
}