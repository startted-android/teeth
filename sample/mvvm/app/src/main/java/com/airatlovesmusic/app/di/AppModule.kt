package com.airatlovesmusic.app.di

import android.content.Context
import com.airatlovesmusic.app.ScreensImpl
import com.airatlovesmusic.global.Screens
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import toothpick.config.Module

class AppModule(context: Context) : Module() {
    init {
        bind(Context::class.java).toInstance(context)
        bind(Screens::class.java).toInstance(ScreensImpl())
        // navigation
        val cicerone = Cicerone.create()
        bind(Router::class.java).toInstance(cicerone.router)
        bind(NavigatorHolder::class.java).toInstance(cicerone.navigatorHolder)
    }
}