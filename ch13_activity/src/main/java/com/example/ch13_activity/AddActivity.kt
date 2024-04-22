package com.example.ch13_activity

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ch13_activity.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_add)
        val binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 액션바에 업버튼(뒤로가기) 붙이기
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // 인텐트에서 키를 통해 데이터 가져오기
        val intent = intent
        val date = intent.getStringExtra("today")
        binding.date.text = date

        // 버튼을 누르면... add 액티비티를 호출했던 main 액티비티로 돌아가겠다
        binding.btnSave.setOnClickListener {
            // MainActivity에 투두 문자열 전달
            intent.putExtra("todo", binding.addEditView.text.toString())
            
            // 결과 코드와 인텐트 전달
            setResult(Activity.RESULT_OK, intent)
            
            finish()
            true
        }
    }

    // 업버튼 누르면 뒤로가기 수행
    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()
    }
}