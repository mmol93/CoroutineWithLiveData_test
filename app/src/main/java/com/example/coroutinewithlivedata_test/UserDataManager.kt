package com.example.coroutinewithlivedata_test

import kotlinx.coroutines.*

class UserDataManager {
    var counter = 0
    private lateinit var deferred :Deferred<Int>
    suspend fun getUserData() : Int{
        coroutineScope {
            launch(Dispatchers.IO) {
                delay(5000)
                counter = 50
            }
            deferred = async(Dispatchers.IO) {
                delay(7000)
                return@async 70
            }
        }
        return counter + deferred.await()
    }
}