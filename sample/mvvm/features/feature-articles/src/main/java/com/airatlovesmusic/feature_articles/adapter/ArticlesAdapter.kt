package com.airatlovesmusic.feature_articles.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.airatlovesmusic.feature_articles.R
import com.airatlovesmusic.model.Article

class ArticlesAdapter: RecyclerView.Adapter<ArticleHolder>() {

    private var list = listOf<Article>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false)
        return ArticleHolder(itemView)
    }

    override fun onBindViewHolder(holder: ArticleHolder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount() = list.size

    fun updateList(newList: List<Article>) {
        this.list = newList
        notifyDataSetChanged()
    }

}