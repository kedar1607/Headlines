package com.example.headlines.features.articles.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.example.headlines.features.articles.models.Article

/**
 * This diff util is used to compare two different articles
 */
class ArticlesDiffUtils : DiffUtil.ItemCallback<Article>() {

    override fun areItemsTheSame(old: Article, aNew: Article): Boolean {
        return old.url == aNew.url
    }

    override fun areContentsTheSame(old: Article, aNew: Article): Boolean {
        return old == aNew
    }

}