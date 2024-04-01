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
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.startButton.text = "시작"
//        binding.startButton.textSize = 24.0f
        
        var prevTime = 0L

        binding.startButton.setOnClickListener {
            binding.chronometer.base = SystemClock.elapsedRealtime() + prevTime
            binding.chronometer.start()

            binding.startButton.isEnabled = false
            binding.stopButton.isEnabled = true
            binding.resetButton.isEnabled = true
        }
        binding.stopButton.setOnClickListener {
            prevTime = binding.chronometer.base - SystemClock.elapsedRealtime() // stop이 클릭됐을 때 타이머 시간
            binding.chronometer.stop()

            binding.stopButton.isEnabled = false
            binding.startButton.isEnabled = true
            binding.resetButton.isEnabled = true
        }
        binding.resetButton.setOnClickListener {
            prevTime = 0L
            binding.chronometer.stop()

            binding.resetButton.isEnabled = false
            binding.startButton.isEnabled = true
            binding.stopButton.isEnabled = false
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when (keyCode) {
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
            KeyEvent.KEYCODE_VOLUME_UP -> Log.d("mobileapp", "VOLUME_UP key가 눌렸습니다.")
            KeyEvent.KEYCODE_VOLUME_DOWN -> Log.d("mobileapp", "VOLUME_DOWN key가 눌렸습니다.")
        }
        return super.onKeyDown(keyCode, event)
    }
}