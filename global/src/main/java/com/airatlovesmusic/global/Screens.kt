package com.airatlovesmusic.global

import ru.terrakok.cicerone.android.support.SupportAppScreen

abstract class Screens {
    abstract fun mainFlow(): SupportAppScreen
    abstract fun articles(): SupportAppScreen
}