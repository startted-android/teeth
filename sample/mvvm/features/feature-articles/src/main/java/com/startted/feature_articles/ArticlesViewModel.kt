package com.startted.feature_articles

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.startted.core_network.data.ApiClient
import com.startted.model.Article
import kotlinx.coroutines.launch
import javax.inject.Inject

class ArticlesViewModel @Inject constructor(
    apiClient: ApiClient
): ViewModel() {

    val list = MutableLiveData<List<Article>>().apply {
        viewModelScope.launch {
            val articles = apiClient.getArticles()
            postValue(articles)
        }
    }

}