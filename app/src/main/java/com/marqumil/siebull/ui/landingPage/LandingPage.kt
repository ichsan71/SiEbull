package com.marqumil.siebull.ui.landingPage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
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
import me.relex.circleindicator.CircleIndicator

class LandingPage : AppCompatActivity() {

    private lateinit var binding: ActivityLandingPageBinding
    lateinit var indicator: CircleIndicator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLandingPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        // Model factory
        val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
        val viewModel: NewsViewModel by viewModels {
            factory
        }

        // News adapter
        val newsAdapter = NewsAdapter { news ->
            if (news.isBookmarked) {
                viewModel.deleteNews(news)
            } else {
                viewModel.saveNews(news)
            }
        }

        // Bottom navigation
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

        // View pager slider
        val images = listOf(R.drawable.bullying_one, R.drawable.bullying_two, R.drawable.bullying_three)
        val viewPager = findViewById<ViewPager>(R.id.viewPager)
        val adapter = ImageAdapter(this, images)
        viewPager.adapter = adapter
        adapter.setViewPager(viewPager)
        indicator = binding.indicator
        indicator.setViewPager(viewPager)
        adapter.setViewPager(viewPager)

        // Card view click listener
        binding.cardBullypedia.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
        binding.cardAskbully.setOnClickListener {
            val intent = Intent(this, AskbullyActivity::class.java)
            startActivity(intent)
        }
        binding.cardHallobully.setOnClickListener {
            val intent = Intent(this, HallobullyActivity::class.java)
            startActivity(intent)
        }

        // News adapter call view model
        viewModel.getBookmarkedNews().observe(this) { bookmarkedNews ->
            newsAdapter.submitList(bookmarkedNews)
            Log.d("Landing page", "Success bookmarked ${bookmarkedNews.size}")
        }

        // News adapter recycler view
        binding.rvNews.layoutManager = LinearLayoutManager(this)
        binding.rvNews.setHasFixedSize(true)
        binding.rvNews.adapter = newsAdapter


    }
}