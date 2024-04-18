package com.example.ch11_jetpack

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.ch11_jetpack.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    // 프래그먼트 어댑터
    class MyFragmentPagerAdapter(activity: FragmentActivity): FragmentStateAdapter(activity) {
        // 프래그먼트 리스트
        val fragments: List<Fragment>
        init {
            fragments = listOf(OneFragment(), TwoFragment(), ThreeFragment())
        }

        // 반드시 오버라이드
        // 프래그먼트 개수 반환
        override fun getItemCount(): Int {
            return fragments.size
        }

        // 반드시 오버라이드
        // 인덱스가 position인 프래그먼트 반환
        override fun createFragment(position: Int): Fragment {
            return fragments[position]
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 클래스를 만들어서 프래그먼트 어댑터 설정
        binding.viewpager.adapter = MyFragmentPagerAdapter(this)
        
        // 탭과 뷰 페이저를 연결
        TabLayoutMediator(binding.tabs, binding.viewpager) {
            tab, position ->
                tab.text = " Tab ${position + 1}" // 탭의 이름 설정
        }.attach()
    }
}