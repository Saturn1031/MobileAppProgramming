package com.example.ch10_notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.graphics.drawable.AnimationDrawable
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.ch10_notification.databinding.ActivityMainBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import java.util.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Animation
        binding.btnTranslate.setOnClickListener {
            val anim = AnimationUtils.loadAnimation(this, R.anim.translate)
            binding.btnTranslate.startAnimation(anim)
        }

        binding.btnScale.setOnClickListener {
            val anim = AnimationUtils.loadAnimation(this, R.anim.scale)
            binding.btnScale.startAnimation(anim)
        }

        binding.btnRotate.setOnClickListener {
            val anim = AnimationUtils.loadAnimation(this, R.anim.rotate)
            binding.btnRotate.startAnimation(anim)
        }

        binding.btnAlpha.setOnClickListener {
            val anim = AnimationUtils.loadAnimation(this, R.anim.alpha)
            binding.btnAlpha.startAnimation(anim)
        }

        binding.btnWave.setOnClickListener {
            val anim = AnimationUtils.loadAnimation(this, R.anim.wave)
            binding.btnWave.startAnimation(anim)
        }

        val rocketAnimation: AnimationDrawable
        val rocketImage = binding.rocketImage.apply {
            setBackgroundResource(R.drawable.rocket)
            rocketAnimation = background as AnimationDrawable
        }
//        rocketAnimation.start()
        binding.rocketImage.setOnClickListener {
            rocketAnimation.start()
        }

        // Youtube
        val random = Random()
        val num = random.nextInt(5)
        lifecycle.addObserver(binding.youtubePlayerView)
        binding.youtubePlayerView.addYouTubePlayerListener(object: AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                val videoId: String
                if (num < 2) {
                    videoId = "WHrANzPs9ww"
                } else {
                    videoId = "dQw4w9WgXcQ"
                }
                youTubePlayer.cueVideo(videoId, 0f)
            }
        })

        // 알림 notification
        val permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions() ) {
            if (it.all { permission -> permission.value == true }) {
                noti()
            }
            else {
                Toast.makeText(this, "permission denied...", Toast.LENGTH_SHORT).show()
            }
        }

        binding.notificationButton.setOnClickListener {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                if (ContextCompat.checkSelfPermission(this,"android.permission.POST_NOTIFICATIONS") == PackageManager.PERMISSION_GRANTED) {
                    noti()
                }
                else {
                    permissionLauncher.launch( arrayOf( "android.permission.POST_NOTIFICATIONS"  ) )
                }
            }
            else {
                noti()
            }
        } // binding.notificationButton

    } // override fun onCreate

    fun noti(){
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val builder: NotificationCompat.Builder
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){     // 26 버전 이상
            val channelId="one-channel"
            val channelName="My Channel One"
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {   // 채널에 다양한 정보 설정
                description = "My Channel One Description"
                setShowBadge(true)  // 앱 런처 아이콘 상단에 숫자 배지를 표시할지 여부를 지정
                val uri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                val audioAttributes = AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .build()
                setSound(uri, audioAttributes)
                enableVibration(true)
            }
            // 채널을 NotificationManager에 등록
            manager.createNotificationChannel(channel)
            // 채널을 이용하여 builder 생성
            builder = NotificationCompat.Builder(this, channelId)
        }
        else {  // 26 버전 이하
            builder = NotificationCompat.Builder(this)
        }

        // 알림의 기본 정보
        builder.run {
            setSmallIcon(R.drawable.small)
            setWhen(System.currentTimeMillis())
            setContentTitle("홍길동")
            setContentText("안녕하세요.")
            setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.big))
        }

        manager.notify(11, builder.build())
    }
}