package com.startted.global.system

import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppScreen

class FlowRouter(private val appRouter: Router) : Router() {

    fun startFlow(screen: SupportAppScreen) {
        appRouter.navigateTo(screen)
    }

    fun newRootFlow(screen: SupportAppScreen) {
        appRouter.newRootScreen(screen)
    }

    fun finishFlow() {
        appRouter.exit()
    }
}