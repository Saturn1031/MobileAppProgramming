package com.example.ch18_image

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ch18_image.databinding.ActivityPhpBinding

class PhpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPhpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnPhp.setOnClickListener {

        }
    }
}