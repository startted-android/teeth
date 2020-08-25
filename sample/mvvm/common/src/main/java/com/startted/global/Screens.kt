package com.startted.global

import ru.terrakok.cicerone.android.support.SupportAppScreen

abstract class Screens {
    abstract fun mainFlow(): SupportAppScreen
    abstract fun articles(): SupportAppScreen
}