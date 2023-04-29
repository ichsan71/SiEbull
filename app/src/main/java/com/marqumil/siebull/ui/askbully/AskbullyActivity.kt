package com.marqumil.siebull.ui.askbully

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.marqumil.siebull.databinding.ActivityAskbullyBinding
import com.marqumil.siebull.ui.hallobully.HallobullyActivity
import com.marqumil.siebull.ui.landingPage.LandingPage

class AskbullyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAskbullyBinding

    private val AskviewModel: AskbullyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAskbullyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()


        AskviewModel.labelItem.observe(this) { labelItem ->
            when (labelItem.label) {
                "LABEL_0" -> {
                    showAlertDialog("Kamu Tidak Mengalami Pembullyan",
                        "Dari cerita yang kamu masukan tadi, kamu tidak terindikasi tindak bullying.\n" +
                                "Untuk lebih yakin, Konsultasikan masalah kamu di Hallobully !")
                }
                "LABEL_1" -> {
                    showAlertDialog("Kamu Mengalami Pembullyan",
                        "Dari cerita yang kamu masukan tadi, kamu terindikasi tindak bullying.\n" +
                                "Kamu bisa laporkan ke KPAI. Konsultasikan masalah kamu di Hallobully !")
                }
                else -> {
                    showAlertDialog("Kamu Mengalami Pembullyan",
                        "Dari cerita yang kamu masukan tadi, kamu terindikasi tindak bullying.\n" +
                                "Kamu bisa laporkan ke KPAI. Konsultasikan masalah kamu di Hallobully !")
                }
            }
        }

        binding.btnKirim.setOnClickListener { view ->
            AskviewModel.postLabel(binding.textInputEditText.toString())
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }

        binding.toolbarBack.setOnClickListener {
            onBackPressed()
            Intent(this, LandingPage::class.java).also {
                startActivity(it)
            }
        }

    }

    private fun showAlertDialog(title: String, message: String) {
        this.let {
            MaterialAlertDialogBuilder(it)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Konsultasikan Sekarang!") { dialog, which ->
                    // Respond to positive button press
                    // Move with intent to HallobullyActivity
                    val moveIntent = Intent(this, HallobullyActivity::class.java)
                    startActivity(moveIntent)
                }
                .setNegativeButton("Cerita lagi") { dialog, which ->
                    // Respond to negative button press
                }
                .show()
        }
    }
}