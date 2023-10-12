package com.creative.shadow.text.name.appiskey_androidapp_task

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.creative.shadow.text.name.appiskey_androidapp_task.koin.moduleList
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext

class AppClass : Application() {

    override fun onCreate() {
        super.onCreate()

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        initKoin()

    }

    private fun initKoin() {
        GlobalContext.startKoin {
            androidContext(this@AppClass)
            modules(moduleList)
        }
    }


}