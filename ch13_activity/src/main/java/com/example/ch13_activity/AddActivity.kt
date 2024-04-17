package com.example.ch13_activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ch13_activity.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_add)
        val binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 버튼을 누르면... add 액티비티를 호출했던 main 액티비티로 돌아가겠다
        binding.btnAdd.setOnClickListener {
            finish()
            true
        }
    }
}