package com.airatlovesmusic.mainflow

import com.airatlovesmusic.global.Screens
import com.airatlovesmusic.global.base.FlowFragment
import javax.inject.Inject

class MainFlowFragment: FlowFragment() {

    @Inject
    lateinit var screens: Screens

    override fun getLaunchScreen() = screens.articles()

}