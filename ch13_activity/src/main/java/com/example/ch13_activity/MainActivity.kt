package com.example.ch13_activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ch13_activity.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // 버튼을 누르면... Add 액티비티로 넘어가겠다
        binding.btnMain.setOnClickListener { 
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
    }
}