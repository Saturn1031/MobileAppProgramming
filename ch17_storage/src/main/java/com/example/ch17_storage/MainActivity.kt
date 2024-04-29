package com.example.ch17_storage

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ch17_storage.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.File
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    var datas: MutableList<String>? = null
    lateinit var adapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // db에서 검색하기
        datas = mutableListOf<String>()
        val db = DBHelper(this).readableDatabase
        val cursor = db.rawQuery("select * from my_table", null)
        while (cursor.moveToNext()) {
            datas?.add(cursor.getString(1))
        }
        db.close()

        // 번들에 저장하는 코드
//        datas  = savedInstanceState?.let {
//            it.getStringArrayList("datas")?.toMutableList()
//        }
//            ?: let {
//                mutableListOf<String>()
//            }

        // 파일 읽기
        val file = File(filesDir, "myFile.txt")
        val readstream : BufferedReader = file.reader().buffered()
        binding.lastsaved.text = "마지막 저장시간: " + readstream.readLine()

        adapter=MyAdapter(datas)
        binding.recyclerView.adapter=adapter
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager=layoutManager
        binding.recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        val requestLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
            it.data!!.getStringExtra("result")?.let {// "result"에 값이 저장되어 있으면(non-null)
                if(it != "") {
                    datas?.add(it)
                    adapter.notifyDataSetChanged()

                    // 파일 읽기
                    val file = File(filesDir, "myFile.txt")
                    val readstream : BufferedReader = file.reader().buffered()
                    binding.lastsaved.text = "마지막 저장시간: " + readstream.readLine()
                }
            }
        }

        binding.mainFab.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)

            val dateFormat = SimpleDateFormat("yyyy-MM-dd") // 년 월 일
            intent.putExtra("today",dateFormat.format(System.currentTimeMillis()))

            requestLauncher.launch(intent)
        }
    }

    // 번들에 저장하는 코드
//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        outState.putStringArrayList("datas", ArrayList(datas))  // 지금까지의 datas 저장
//    }
}