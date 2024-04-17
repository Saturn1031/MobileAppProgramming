package com.example.ch6_view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

// 액티비티: 화면을 구성하는 컴포넌트
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // xml로 작성한 뷰를 화면에 출력
    }
}