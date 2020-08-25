package com.startted.feature_articles

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.startted.global.base.BaseFragment
import com.startted.lists.articles.ArticlesAdapter
import kotlinx.android.synthetic.main.fragment_articles.*

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
        rv_articles.layoutManager = LinearLayoutManager(context)
        rv_articles.adapter = adapter
        rv_articles.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }

}