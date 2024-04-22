package com.example.ch13_activity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ch13_activity.databinding.ItemRecyclerviewBinding

// MyAdapter 클래스 생성
class MyViewHolder(val binding: ItemRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root)

// datas에 담긴 것이 없을 수 있으므로 datas에 null 허용
class MyAdapter(val datas: MutableList<String>?): RecyclerView.Adapter<RecyclerView.ViewHolder>(){ // 2-1
    override fun getItemCount(): Int {
        // ?:0 -> null이면 0 반환
        return datas?.size ?:0;     // 2-2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent,false))
    }

    // null이 아닌 datas와 recycler view 연결
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as MyViewHolder).binding
        // !! -> null이 아닌 값
        binding.itemData.text = datas!![position]      // 2-3
    }
}