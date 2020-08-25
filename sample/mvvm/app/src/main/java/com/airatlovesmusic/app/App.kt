package com.airatlovesmusic.app

import android.app.Application
import com.airatlovesmusic.app.di.AppModule
import com.airatlovesmusic.core_network.di.ServerModule
import com.airatlovesmusic.global.di.DI
import toothpick.Toothpick
import toothpick.configuration.Configuration

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        toothpick()
        appScope()
    }

    private fun toothpick() {
        if (BuildConfig.DEBUG) {
            Toothpick.setConfiguration(Configuration.forDevelopment().preventMultipleRootScopes())
        } else {
            Toothpick.setConfiguration(Configuration.forProduction())
        }
    }

    private fun appScope() {
        Toothpick.openScope(DI.APP_SCOPE)
            .installModules(AppModule(this), ServerModule())
    }

}