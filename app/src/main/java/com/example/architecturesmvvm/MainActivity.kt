package com.example.architecturesmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.example.architectures.model.LocalDataSource
import com.example.architectures.model.Movie
import com.example.architectures.model.RemoteDataSource
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map

class MainActivity : AppCompatActivity(), LifecycleOwner {
    private lateinit var source : LocalDataSource
    private lateinit var lifecycleRegistry: LifecycleRegistry

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycleRegistry = LifecycleRegistry(this)

        source = LocalDataSource(application)
        val remote = RemoteDataSource()
        lifecycle.addObserver(source)
     //   source.getAllMvovies()
    }

    override fun onStart() {
        super.onStart()
        lifecycleRegistry.currentState = Lifecycle.State.STARTED
    }
    override fun onPause() {
       // lifecycleRegistry.currentState = Lifecycle.State.RESUMED
        super.onPause()
    }


}