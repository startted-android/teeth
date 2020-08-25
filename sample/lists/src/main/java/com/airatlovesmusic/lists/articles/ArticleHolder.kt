package com.airatlovesmusic.lists.articles

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.airatlovesmusic.model.Article
import kotlinx.android.synthetic.main.item_article.view.*

class ArticleHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bind(data: Article) {
        itemView.tv_title.text = data.title
        itemView.tv_url.text = data.url
    }
}