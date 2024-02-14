package com.borja.android.basicnotifications

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val extras = intent.extras
        if(extras!=null){
            val name1 = extras.getString("example1")
            Log.i("BorchxData","El valor: $name1")
            val name2 = extras.getString("example2")
            Log.i("BorchxData","El valor: $name2")
        }
    }
}