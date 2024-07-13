package com.example.ticketapp

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.data.di.dataModule
import com.example.presentation.di.airlineTicketsModule
import com.example.presentation.di.hotelsModule
import com.example.presentation.di.inShortModule
import com.example.presentation.di.profileModule
import com.example.presentation.di.subscriptionsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                airlineTicketsModule,
                hotelsModule,
                inShortModule,
                profileModule,
                subscriptionsModule,
                dataModule
            )
        }
    }
}