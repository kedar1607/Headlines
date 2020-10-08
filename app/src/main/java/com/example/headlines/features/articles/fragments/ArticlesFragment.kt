package com.example.headlines.features.articles.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.headlines.databinding.FragmentArticlesBinding
import com.example.headlines.features.articles.adapters.ArticlesAdapter
import com.example.headlines.features.articles.destinations.ArticlesActivityDestination
import com.example.headlines.features.articles.models.Article
import com.example.headlines.features.articles.states.ArticleScreenState
import com.example.headlines.features.articles.viewmodels.ArticlesDestinationViewModel
import com.example.headlines.features.articles.viewmodels.ArticlesViewModel
import com.example.headlines.utils.helpers.ViewStateHelper
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class ArticlesFragment: Fragment() {

    private var _binding: FragmentArticlesBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val destinationViewModel: ArticlesDestinationViewModel by activityViewModels { viewModelFactory }

    private val articlesViewModel: ArticlesViewModel by activityViewModels { viewModelFactory }

    private lateinit var viewStateHelper: ViewStateHelper

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArticlesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? AppCompatActivity)?.supportActionBar?.hide()
        viewStateHelper = ViewStateHelper(binding.contentView, binding.errorView, binding.pbLoading)
        binding.btnCancel.setOnClickListener { destinationViewModel.finish() }
        observeArticlesLoading()
        observeStartUpState()
    }

    /**
     * Populates all of the sources provided List<Articles>
     */
    private fun populateArticles(sources: List<Article>) {
        val adapter = ArticlesAdapter(::onArticleClicked)
        binding.rcvArticles.adapter = adapter
        adapter.submitList(sources)
    }

    private fun observeArticlesLoading(){
        articlesViewModel.articlesScreenState.observe(viewLifecycleOwner, Observer { articleScreenState ->
            when(articleScreenState){
                ArticleScreenState.Landing, ArticleScreenState.Loading -> viewStateHelper.showLoading()
                is ArticleScreenState.ArticlesFetched -> {
                    populateArticles(articleScreenState.articles)
                    viewStateHelper.showContent()
                }
                // This logic can essentially be changed to show the error screen but at this time we can just consider the network error and invalid source id as one and the same
                // for the purpose of this sample app.
                ArticleScreenState.ArticlesFetchFailed -> destinationViewModel.invalidSourceId()
            }
        })
    }

    private fun observeStartUpState() {
        destinationViewModel.destinations.observe(viewLifecycleOwner, Observer {
            when(it){
                is ArticlesActivityDestination.StartUp -> articlesViewModel.fetchArticlesBySourceId(it.sourceId)
            }
        })
    }

    private fun onArticleClicked(article: Article){

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}