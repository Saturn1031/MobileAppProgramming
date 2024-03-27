package com.example.ch08_event

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ch08_event.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startButton.setOnClickListener {
            binding.startButton.text = "시작"
            binding.startButton.textSize = 24.0f
        }
        binding.stopButton.setOnClickListener {

        }
        binding.resetButton.setOnClickListener {

        }
    }
}