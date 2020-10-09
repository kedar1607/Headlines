package com.example.headlines.features.articles.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.headlines.databinding.ItemArticleBinding
import com.example.headlines.features.articles.models.Article
import com.example.headlines.utils.extensions.share
import com.example.headlines.utils.extensions.toDate

/**
 * View item for article [Article]
 * in the [com.example.headlines.features.articles.adapters.ArticlesAdapter].
 */
class ArticleItemViewHolder(private val binding: ItemArticleBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(article: Article, onClick: ((Article) -> Unit)) {
        binding.tvTitle.text = article.title
        /**
         * Ideally, this should be done when constructing a model from the network response to avoid any extra computation on the main thread.
         */
        binding.tvDateTime.text = article.publishedAt.toDate()
        binding.shareBtn.setOnClickListener {
            binding.root.context.share(article.url)
        }
        Glide.with(binding.root.context)
            .load(article.urlToImage)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(binding.imgArticle)

        itemView.setOnClickListener {
            onClick.invoke(article)
        }
    }
}