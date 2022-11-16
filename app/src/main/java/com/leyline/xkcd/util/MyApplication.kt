package com.leyline.xkcd.util

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.leyline.xkcd.networkingModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(applicationContext)
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(networkingModule)
        }
    }
}