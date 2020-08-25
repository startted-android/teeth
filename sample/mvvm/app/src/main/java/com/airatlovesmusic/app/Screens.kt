package com.airatlovesmusic.app

import com.airatlovesmusic.feature_articles.ArticlesFragment
import com.airatlovesmusic.global.Screens
import com.airatlovesmusic.mainflow.MainFlowFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class ScreensImpl: Screens() {
    override fun mainFlow() = object : SupportAppScreen() {
        override fun getFragment() = MainFlowFragment()
    }

    override fun articles() = object : SupportAppScreen() {
        override fun getFragment() = ArticlesFragment()
    }
}