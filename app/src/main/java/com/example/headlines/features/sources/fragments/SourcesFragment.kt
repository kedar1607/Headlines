package com.example.headlines.features.sources.fragments

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
import com.example.headlines.databinding.FragmentSourcesBinding
import com.example.headlines.features.sources.adapters.SourcesAdapter
import com.example.headlines.features.sources.models.Source
import com.example.headlines.features.sources.states.SourcesScreenState
import com.example.headlines.features.sources.viewmodels.SourcesDestinationViewModel
import com.example.headlines.features.sources.viewmodels.SourcesViewModel
import com.example.headlines.utils.helpers.ViewStateHelper
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

/**
 * This fragment displays the list of all the sources with their name and description. This is the entry point to the application.
 */
class SourcesFragment: Fragment() {

    private var _binding: FragmentSourcesBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewStateHelper: ViewStateHelper

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val sourcesViewModel: SourcesViewModel by activityViewModels { viewModelFactory }

    private val destinationViewModel: SourcesDestinationViewModel by activityViewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSourcesBinding.inflate(inflater, container, false)
        viewStateHelper = ViewStateHelper(binding.contentView, binding.errorView, binding.pbLoading)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? AppCompatActivity)?.supportActionBar?.hide()
        sourcesViewModel.sourceScreenState.observe(viewLifecycleOwner, Observer {
            when(it){
                SourcesScreenState.Landing, SourcesScreenState.Loading -> viewStateHelper.showLoading()
                is SourcesScreenState.SourcesFetched -> {
                    populateSources(it.sources)
                    viewStateHelper.showContent()
                }
                SourcesScreenState.SourcesFetchFailed -> viewStateHelper.showError()
            }
        })
    }

    /**
     * Populates all of the sources provided List<Sources>
     */
    private fun populateSources(sources: List<Source>) {
        val adapter = SourcesAdapter(::onSourceSelected)
        binding.rcvSources.adapter = adapter
        adapter.submitList(sources)
    }

    /**
     * Do this when source is selected by user.
     */
    private fun onSourceSelected(source: Source){
        destinationViewModel.navigateToArticles(source.id)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        /**
         * To avoid mem. leaks
         */
        _binding = null
    }
}