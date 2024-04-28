package com.dicoding.asclepius.view.result

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.dicoding.asclepius.databinding.ActivityResultBinding
import com.dicoding.asclepius.view.utils.ViewModelFactory
import androidx.activity.viewModels
import com.dicoding.asclepius.data.local.entity.History
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    private val resultViewModel by viewModels<ResultViewModel>() {
        ViewModelFactory.getInstance(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Result"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val imageUri = Uri.parse(intent.getStringExtra(IMAGE_URI))
        val displayResult = intent.getStringExtra(DISPLAY_RESULT)

        imageUri?.let {
            binding.resultImage.setImageURI(it)
            binding.resultText.text = displayResult
        }

        binding.saveButton.setOnClickListener {
            val history = History()
            history.image = imageUri.toString()
            history.result = displayResult
            history.date = getCurrentDateTime()
            resultViewModel.insert(history)

            showSuccessMessage()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getCurrentDateTime(): String {
        val calendar = Calendar.getInstance()
        val locale = Locale("id")
        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", locale)
        return dateFormat.format(calendar.time)
    }

    private fun showSuccessMessage() {
        Snackbar.make(binding.root, "Data berhasil disimpan di History", Snackbar.LENGTH_SHORT).show()
    }

    companion object {
        const val IMAGE_URI = "image_uri"
        const val DISPLAY_RESULT = "display_result"
    }
}