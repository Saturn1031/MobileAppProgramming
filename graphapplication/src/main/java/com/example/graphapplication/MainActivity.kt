package com.example.graphapplication

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.graphapplication.databinding.ActivityMainBinding
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val linevalues: ArrayList<Entry> = ArrayList()
//        linevalues.add(Entry(2, 2))
//        linevalues.add(Entry(1, 2))
        for (i in 0 until 10) {
            val v = Math.random() * 10
            linevalues.add(Entry(i.toFloat(), v.toFloat()))
        }
        val linedataset = LineDataSet(linevalues, "LineDataSet") // 수치, 표현
        linedataset.color = Color.YELLOW
        linedataset.lineWidth = 5f
        linedataset.setCircleColor(Color.MAGENTA)
        val linedata = LineData(linedataset)
        binding.lineChart.data = linedata
    }
}