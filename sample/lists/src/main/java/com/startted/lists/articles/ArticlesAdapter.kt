package com.startted.lists.articles

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.startted.lists.R
import com.startted.model.Article

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