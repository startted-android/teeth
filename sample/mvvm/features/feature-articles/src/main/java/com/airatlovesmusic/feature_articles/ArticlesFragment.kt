package com.airatlovesmusic.feature_articles

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.airatlovesmusic.feature_articles.adapter.ArticlesAdapter
import com.airatlovesmusic.global.base.BaseFragment
import com.airatlovesmusic.resources.rvArticles

class ArticlesFragment: BaseFragment() {

    override val layoutRes: Int
        get() = R.layout.fragment_articles

    private val viewModel by scope<ArticlesViewModel>()

    private val adapter by lazy { ArticlesAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.list bindTo { adapter.updateList(it) }
    }

    private fun initRecycler() {
        rvArticles.layoutManager = LinearLayoutManager(context)
        rvArticles.adapter = adapter
        rvArticles.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }

}