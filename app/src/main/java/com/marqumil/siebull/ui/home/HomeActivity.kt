package com.marqumil.siebull.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.marqumil.siebull.R
import com.marqumil.siebull.data.Result
import com.marqumil.siebull.databinding.ActivityHomeBinding
import com.marqumil.siebull.ui.ViewModelFactory
import com.marqumil.siebull.ui.bookmark.BookmarkActivity
import com.marqumil.siebull.ui.landingPage.LandingPage
import com.marqumil.siebull.ui.news.NewsAdapter
import com.marqumil.siebull.ui.news.NewsViewModel

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.toolbarFav.setOnClickListener {
            val intent = Intent(this, BookmarkActivity::class.java)
            startActivity(intent)
        }

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

        viewModel.getHeadlineNews().observe(this) { result ->
            if (result != null) {
                when (result) {
                    is Result.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }

                    is Result.Loading -> binding.progressBar.visibility = View.VISIBLE

                    is Result.Success -> {
                        binding.progressBar.visibility = View.GONE
                        newsAdapter.submitList(result.data)
                        Log.d("NewsFragment", "Success ${result.data.size}")
                    }
                }
            }
        }

        with(binding.rvBookmark) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = newsAdapter
        }

        binding.toolbarBack.setOnClickListener {
            onBackPressed()
            Intent(this, LandingPage::class.java).also {
                startActivity(it)
            }
        }

    }




}