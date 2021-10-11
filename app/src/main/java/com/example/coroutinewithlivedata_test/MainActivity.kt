package com.example.coroutinewithlivedata_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.coroutinewithlivedata_test.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private var counter = 0
    private lateinit var binder : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // clickButton 버튼 클릭 시 카운터 up
        binder.clickButton.setOnClickListener {
            ++counter
            binder.countTextView.text = counter.toString()
        }

        // downloadButton 버튼 클릭 시 코루틴(백그라운드)에서 무거운 처리 하기
        binder.downloadButton.setOnClickListener {
            // 메인스레드에서 처리하는 무거운 일을 코루틴으로 시키기
            // CoroutineScope: 코루틴 스코프를 사용하기 위한 인터페이스
            // GlobalScope: 코루틴중에서 최상위 등급 = 앱이 종료될 때까지 실행됨
            // Dispatchers: 코로틴을 어느 코루틴에서 실행할지 정의
            // launch: 코루틴 빌더, 새로운 코루틴을 생성 & 실행
            CoroutineScope(Dispatchers.Main).launch {
                binder.userTextView.text = UserDataManager2().getTotalUserCount().toString()
            }
        }
    }

    private fun downloadUserData(){
        for (i in 1..200000){
            Log.d("TAG", "i: $i")
        }
    }
}