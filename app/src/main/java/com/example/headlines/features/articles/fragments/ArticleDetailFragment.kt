package com.example.headlines.features.articles.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.headlines.databinding.FragmentArticleDetailBinding
import com.example.headlines.features.articles.models.Article
import com.example.headlines.features.articles.viewmodels.ArticlesDestinationViewModel
import com.example.headlines.utils.extensions.*
import com.example.headlines.utils.helpers.ViewStateHelper
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

/**
 * This fragment displays details of the article that user tapped on in [ArticlesFragment]'s list of [Article]s
 * In this specific case, there is no network call in this fragment. The argument of type [Article] is passed through [navArgs].
 * This argument is added in the navigation graph (Check nav_articles.xml for more info.)
 */
class ArticleDetailFragment: Fragment() {

    private var _binding: FragmentArticleDetailBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val destinationViewModel: ArticlesDestinationViewModel by activityViewModels { viewModelFactory }

    private var _viewStateHelper: ViewStateHelper? = null
    private val viewStateHelper get() =  _viewStateHelper!!

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArticleDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? AppCompatActivity)?.supportActionBar?.hide()
        _viewStateHelper = ViewStateHelper(binding.contentView, binding.errorView, binding.pbLoading)
        binding.btnCancel.setOnClickListener { destinationViewModel.backToArticlesList() }
        viewStateHelper.showLoading()
        populateArticleData(navArgs<ArticleDetailFragmentArgs>().value.article)
        viewStateHelper.showContent()
    }

    private fun populateArticleData(article: Article){
        // Loads image
        Glide.with(requireContext())
            .load(article.urlToImage)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(binding.imgArticle)

        binding.tvDateTime.text = article.publishedAt.toDate()
        binding.tvTitle.text = article.title.getHTMLText()

        binding.tvDesc.setTexViewVisibleWithHtmlLinks(article.description)
        binding.tvAuthor.setTexViewVisibleWithHtmlLinks(article.author)
        binding.tvContent.setTexViewVisibleWithHtmlLinks(article.content)

        binding.shareBtn.setOnClickListener { requireContext().share(article.url) }
        binding.linkBtn.setOnClickListener { requireContext().openUrlInBrowser(article.url) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        /**
         * Avoid the memory leak
         */
        _binding = null
        _viewStateHelper = null
    }
}