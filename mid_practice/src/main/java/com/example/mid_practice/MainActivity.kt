package com.example.mid_practice

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mid_practice.databinding.ActivityMainBinding
import com.example.mid_practice.databinding.DialogCustomBinding
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var binding: ActivityMainBinding
    lateinit var toggle: ActionBarDrawerToggle

    // 프래그먼트 어댑터
    class FragStateAdapter(activity: FragmentActivity): FragmentStateAdapter(activity) {
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
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 클래스를 만들어서 프래그먼트 어댑터 설정
        binding.viewpager.adapter = FragStateAdapter(this)

        // 탭과 뷰 페이저를 연결
        TabLayoutMediator(binding.tabs, binding.viewpager) {
                tab, position ->
            tab.text = " Tab ${position + 1}" // 탭의 이름 설정
        }.attach()

        // 드로어 메뉴를 열 네비게이션 토글 만들기 (string도 선언할 것)
        toggle = ActionBarDrawerToggle(this, binding.drawer, R.string.drawer_opened, R.string.drawer_closed)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // 화면에 보여주기
        toggle.syncState() // 싱크

        // 드로어 메뉴 listener로 MainActivity 설정
        binding.mainDrawerView.setNavigationItemSelectedListener(this)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // 메뉴에 res/menu/menu_nevigation.xml 적용
        menuInflater.inflate(R.menu.menu_navigation, menu)

        // search view 객체를 가져옴
        val searchView = menu?.findItem(R.id.menu_search)?.actionView as SearchView // 캐스팅은 as 키워드로

        // 커스텀 다이얼로그를 만들기 위해
        // dialog_custom.xml 뷰바인딩
        val dialogBinding = DialogCustomBinding.inflate(layoutInflater)

        val eventHandler3 = object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                if (which == DialogInterface.BUTTON_POSITIVE) {
                    Log.d("mobileapp", "BUTTON_POSITIVE")
                }
            }
        }

        // 커스텀 다이얼로그 만들기
        val customDlg = AlertDialog.Builder(this).run() {
            setTitle("검색어 입력 확인")

            // 다이얼로그 뷰 객체 전달
            setView(dialogBinding.root)

            setPositiveButton("닫기", eventHandler3)

            // 다이얼로그 만듦
            create()
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            // 키보드의 검색 버튼을 클릭하면...
            override fun onQueryTextSubmit(query: String?): Boolean {
                dialogBinding.textView2.text = "$query"
                dialogBinding.textView2.setTextColor(Color.parseColor("#ff0000"))
                customDlg.show()
                return true
            }

            // 검색어가 변경되면...
            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_info -> {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://m.duksung.ac.kr"))
                startActivity(intent)
                true
            }

            R.id.item_call -> {
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:119"))
                startActivity(intent)
                true
            }

            R.id.item_map -> {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:37.6513783,127.0163402"))
                startActivity(intent)
                true
            }
        }
        return false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) { // 우측 상단 토글이 눌렸다면...
            return true // 토글이 가진 기본 기능을 수행
        } else if (item.itemId == R.id.login) {
            Toast.makeText(applicationContext, "개발 중 입니다.", Toast.LENGTH_SHORT).show()
        }

        return super.onOptionsItemSelected(item)
    }
}
