package com.marqumil.siebull.ui.bookmark

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.marqumil.siebull.databinding.ActivityBookmarkBinding
import com.marqumil.siebull.ui.news.NewsAdapter
import com.marqumil.siebull.ui.news.NewsViewModel
import com.marqumil.siebull.ui.ViewModelFactory
import com.marqumil.siebull.ui.home.HomeActivity
import com.marqumil.siebull.ui.landingPage.LandingPage

class BookmarkActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookmarkBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookmarkBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
        val viewModel: NewsViewModel by viewModels {
            factory
        }

        val newsAdapter = NewsAdapter { news ->
            if (news.isBookmarked) {
                viewModel.deleteNews(news)
            } else {
                viewModel.saveNews(news)
            }
        }

        viewModel.getBookmarkedNews().observe(this) { bookmarkedNews ->
            binding.progressBar.visibility = View.GONE
            newsAdapter.submitList(bookmarkedNews)
        }

        with(binding.rvBookmark) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = newsAdapter
        }

        binding.toolbarBack.setOnClickListener {
            onBackPressed()
            Intent(this, HomeActivity::class.java).also {
                startActivity(it)
            }
        }

    }
}