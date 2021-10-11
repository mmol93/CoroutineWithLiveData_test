package com.example.coroutinewithlivedata_test

import kotlinx.coroutines.*

class UserDataManager {
    suspend fun getTotalUserCount() : Int{
        var count = 0
        CoroutineScope(Dispatchers.IO).launch {
            // 실행되고 1초 대기
            delay(1000)
            count = 50
        }
        // async로 실행한 코루틴은 반환 값을 받을 수 있다
        val deferred = CoroutineScope(Dispatchers.IO).async {
            // 실행되고 3초 대기
            delay(3000)
            return@async 70
        }

        // 첫 번째 변수인 count는 기다리지 않기 때문에 초기 값인 0이 바로 return 부분에 들어가고 1초 후에 다시 count에 50을 넣는다
        // 두 번째 변수인 deferred는 await()가 있기 때문에 해당 변수만 끝날 때까지 기다렸다가 넣어준다
        // 즉, delay에 의해 deferred 값을 얻기 위해 3초를 기다리지만 count는 return에 반영되지 않음
        // 그렇기 때문에 이러한 방법은 권장되지 않음
        return count + deferred.await()
    }
}