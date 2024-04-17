package com.example.ch08_event

import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.KeyEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ch08_event.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var initTime = 0L // 처음 BACK 버튼이 눌린 시간
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        setContentView(R.layout.activity_main)
        
//        첫 글자, _ 뒤 글자는 대문자로, 끝에 Binding 추가한 이름의 객체 생김
//        뷰 바인딩 클래스의 inflate() 호출하면 객체 얻어짐
        val binding = ActivityMainBinding.inflate(layoutInflater)
        
//        액티비티 화면 출력하기
        setContentView(binding.root)

//        binding.startButton.text = "시작"
//        binding.startButton.textSize = 24.0f

        // stop 이전 타이머 실행 시간을 저장할 변수
        var prevTime = 0L

//        뷰 이벤트
        // startButton의 onClickListener 설정
        // setOnClickListener는 구현할 메소드가 하나뿐이고, 인수가 하나이기 때문에 아래와 같이 작성 가능 (람다함수)
        binding.startButton.setOnClickListener {

            // chronometer.base에 기준 시간 부여
            binding.chronometer.base = SystemClock.elapsedRealtime() + prevTime

            // 기준 시간으로부터 기기가 얼마나 가동되었는지 측정 시작
            binding.chronometer.start()

            // 버튼 활성화, 비활성화 여부 설정
            binding.startButton.isEnabled = false
            binding.stopButton.isEnabled = true
            binding.resetButton.isEnabled = true
        }

        // stopButton의 onClickListener 설정
        binding.stopButton.setOnClickListener {

            // start 버튼이 눌렸을 때 가동 시간 - 현재 가동 시간
            prevTime = binding.chronometer.base - SystemClock.elapsedRealtime() // stop이 클릭됐을 때 타이머 시간
            binding.chronometer.stop()

            binding.stopButton.isEnabled = false
            binding.startButton.isEnabled = true
            binding.resetButton.isEnabled = true
        }

        // resetButton의 onClickListener 설정
        binding.resetButton.setOnClickListener {
            prevTime = 0L
            binding.chronometer.stop()

            binding.resetButton.isEnabled = false
            binding.startButton.isEnabled = true
            binding.stopButton.isEnabled = false
        }
    } // onCreate()

//    키 이벤트 (onKeyDown: 키를 누른 순간의 이벤트)
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when (keyCode) {

            // back 키가 눌렸을 때
            KeyEvent.KEYCODE_BACK -> {

                // 처음 눌렸을 떄
                if (System.currentTimeMillis() - initTime > 3000) { // 3초 초과
                    Log.d("mobileapp", "BACK key가 눌렸습니다. 종료하려면 한 번 더 누르세요.")
                    initTime = System.currentTimeMillis() // 처음 버튼이 클릭된 시간

                    Toast.makeText(this, "종료하려면 한 번 더 누르세요.", Toast.LENGTH_SHORT).show()
                    return true
                }
                // 두 번째 눌렸을 때
            }

            // volume 키가 눌렸을 때
            KeyEvent.KEYCODE_VOLUME_UP -> Log.d("mobileapp", "VOLUME_UP key가 눌렸습니다.")
            KeyEvent.KEYCODE_VOLUME_DOWN -> Log.d("mobileapp", "VOLUME_DOWN key가 눌렸습니다.")
        }
        return super.onKeyDown(keyCode, event)
    }
}