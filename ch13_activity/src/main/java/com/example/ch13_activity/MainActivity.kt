package com.example.ch13_activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ch13_activity.databinding.ActivityMainBinding
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var datas : MutableList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // datas 뮤터블 리스트 초기화
        // datas = mutableListOf<String>()
        datas = savedInstanceState?.let {
            it.getStringArrayList("datas")?.toMutableList()
        } ?: let {
            // 번들의 "datas"가 null이면...
            mutableListOf<String>()
        }

        // 어댑터 설정
        val adapter = MyAdapter(datas)
        binding.recyclerView.adapter = adapter

        // 투두 세로로 배치 (기본값 vertical)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        
        binding.recyclerView.addItemDecoration(
            // 아이템 구분선 추가
            DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        )

        val requestlauncher : ActivityResultLauncher<Intent> = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            // 콜백 (AddActivity에서 데이터 받음)
            it.data!!.getStringExtra("todo")?.let { // let: null이 아니면 실행
                // datas에 문자열 추가하고 리사이클러뷰에 변경 알림
                datas?.add(it)
                adapter.notifyDataSetChanged()
            }
        }

        // Add Item 플로팅 버튼을 누르면...
        binding.mainFab.setOnClickListener {
            // 인텐트 생성
            val intent = Intent(this, AddActivity::class.java)
            // 인텐트에 엑스트라 데이터 전달 ("키", 값)
            val dateFormat = SimpleDateFormat("yyyy-MM-dd") // 년 월 일 문자열로 변환
            intent.putExtra("today", dateFormat.format(System.currentTimeMillis()))

            // AddActivity 부르기 (startActivity 대신 requestlauncher 사용)
            // startActivity(intent)
            requestlauncher.launch(intent)
        }
    }

    // onCreate를 다시 실행해도 datas 상태 저장하기
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putStringArrayList("datas", ArrayList(datas))
    }
}