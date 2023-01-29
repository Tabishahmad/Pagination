package com.example.bookapi

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication: Application(){
    companion object{
        lateinit var context : Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this;
    }
}