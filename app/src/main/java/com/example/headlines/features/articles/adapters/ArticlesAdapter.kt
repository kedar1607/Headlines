package com.example.headlines.features.articles.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.headlines.databinding.ItemArticleBinding
import com.example.headlines.features.articles.diffutils.ArticlesDiffUtils
import com.example.headlines.features.articles.models.Article
import com.example.headlines.features.articles.viewholders.ArticleItemViewHolder

/**
 * Adapter that shows a list of available articles of type [Article].
 */
class ArticlesAdapter(private val onClick: ((Article) -> Unit)) :
    ListAdapter<Article, ArticleItemViewHolder>(ArticlesDiffUtils()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemArticleBinding.inflate(inflater, parent, false)
        return ArticleItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleItemViewHolder, position: Int) {
        val article = getItem(position)
        holder.bind(article, onClick)
    }

}