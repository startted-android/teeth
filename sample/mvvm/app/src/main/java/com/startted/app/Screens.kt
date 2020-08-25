package com.startted.app

import com.startted.feature_articles.ArticlesFragment
import com.startted.global.Screens
import com.startted.mainflow.MainFlowFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class ScreensImpl: Screens() {
    override fun mainFlow() = object : SupportAppScreen() {
        override fun getFragment() = MainFlowFragment()
    }

    override fun articles() = object : SupportAppScreen() {
        override fun getFragment() = ArticlesFragment()
    }
}