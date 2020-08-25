package com.airatlovesmusic.feature_articles.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.airatlovesmusic.model.Article
import com.airatlovesmusic.resources.tvTitle
import com.airatlovesmusic.resources.tvUrl

class ArticleHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bind(data: Article) {
        itemView.tvTitle.text = data.title
        itemView.tvUrl.text = data.url
    }
}