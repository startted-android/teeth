package com.airatlovesmusic.app.ui

import com.airatlovesmusic.global.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class AppLauncher @Inject constructor(
    private val router: Router,
    private val screens: Screens
) {

    fun coldStart() {
        router.navigateTo(screens.mainFlow())
    }

}