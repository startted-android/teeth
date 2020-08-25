package com.startted.mainflow

import com.startted.global.Screens
import com.startted.global.base.FlowFragment
import javax.inject.Inject

class MainFlowFragment: FlowFragment() {

    @Inject
    lateinit var screens: Screens

    override fun getLaunchScreen() = screens.articles()

}