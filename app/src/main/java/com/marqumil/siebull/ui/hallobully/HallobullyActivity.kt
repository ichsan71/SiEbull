package com.marqumil.siebull.ui.hallobully

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.marqumil.siebull.databinding.ActivityHallobullyBinding
import com.marqumil.siebull.ui.landingPage.LandingPage

class HallobullyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHallobullyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHallobullyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.toolbarBack.setOnClickListener {
            onBackPressed()
            Intent(this, LandingPage::class.java).also {
                startActivity(it)
            }
        }
    }
}