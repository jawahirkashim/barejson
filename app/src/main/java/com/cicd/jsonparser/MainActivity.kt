package com.cicd.jsonparser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {
    private val TAG: String = MainActivity::class.java.simpleName
    private val jsonViewModel : JsonParser by viewModels()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: ")
        setContentView(R.layout.activity_main)
        
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
        coroutineScope.launch {
            val titleList = jsonViewModel.getTitle("epaga");
            for (title in titleList){
                Log.d(TAG, "onStart: tittle $title")
            }
        }
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }
}