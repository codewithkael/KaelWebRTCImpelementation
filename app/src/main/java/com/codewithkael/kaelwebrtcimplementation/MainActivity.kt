package com.codewithkael.kaelwebrtcimplementation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.codewithkael.kaelwebrtcimplementation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var views: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        views = ActivityMainBinding.inflate(layoutInflater)
        setContentView(views.root)
        mainViewModel = MainViewModel(application)
        mainViewModel.init()
        mainViewModel.attachViewGroup(views.frameLayout)

    }

}