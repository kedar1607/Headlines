package com.example.headlines.features.articles.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.headlines.databinding.ItemArticleBinding
import com.example.headlines.features.articles.models.Article

/**
 * View item for article [Article]
 * in the [com.example.headlines.features.articles.adapters.ArticlesAdapter].
 */
class ArticleItemViewHolder(private val binding: ItemArticleBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(article: Article, onClick: ((Article) -> Unit)) {
        binding.tvTitle.text = article.title
        binding.tvDateTime.text = article.publishedAt
        Glide.with(binding.root.context)
            .load(article.urlToImage)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(binding.imgArticle)

        itemView.setOnClickListener {
            onClick.invoke(article)
        }
    }

}