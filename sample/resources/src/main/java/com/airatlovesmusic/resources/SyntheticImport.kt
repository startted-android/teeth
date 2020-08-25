package com.airatlovesmusic.resources

import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_articles.*
import kotlinx.android.synthetic.main.item_article.view.*

inline val View.tvUrl: TextView get() = tv_url
inline val View.tvTitle: TextView get() = tv_title

inline val Fragment.rvArticles get() = rv_articles