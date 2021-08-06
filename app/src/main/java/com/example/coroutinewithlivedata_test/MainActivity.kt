package com.example.coroutinewithlivedata_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.coroutinewithlivedata_test.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binder : ActivityMainBinding
    private lateinit var viewModel : MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binder.mainViewModelId = viewModel
        binder.lifecycleOwner = this

        binder.clickButton.setOnClickListener {
            viewModel.counter.value = (viewModel.counter.value)!!.plus(1)
        }

        binder.downloadButton.setOnClickListener {
            binder.userTextView.text = "0"
                CoroutineScope(Dispatchers.Main).launch{
                binder.userTextView.text = UserDataManager().getUserData().toString()
            }
        }
    }
}