package com.example.mid_practice

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mid_practice.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        val checked = intent.getStringExtra("checked")
        binding.txtMain.text = "${checked.toString()} 추가하기"

        binding.btnSave.setOnClickListener {
            var name = ""
            var phone = ""
            if (checked.toString() == "친구") {
                name = "[친구] ${binding.editName.text.toString()}"
            } else {
                name = "[장소] ${binding.editName.text.toString()}"
            }
            phone = "${binding.editPhone.text.toString()}"

            // MainActivity에 투두 문자열 전달
            intent.putExtra("recycleText", name + '\n' + phone)

            // 결과 코드와 인텐트 전달
            setResult(Activity.RESULT_OK, intent)

            finish()
            true
        }
    }
}