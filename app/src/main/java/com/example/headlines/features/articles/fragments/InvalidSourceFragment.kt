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
import com.example.headlines.databinding.FragmentInvalidSourceBinding
import com.example.headlines.features.articles.viewmodels.ArticlesDestinationViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

/**
 * This fragment is used to show the error screen with error message and the button to close the articles workflow
 * started by [ArticlesFragment] in [com.example.headlines.features.articles.activities.ArticlesActivity]
 */
class InvalidSourceFragment: Fragment() {

    private var _binding: FragmentInvalidSourceBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val destinationViewModel: ArticlesDestinationViewModel by activityViewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInvalidSourceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? AppCompatActivity)?.supportActionBar?.hide()
        binding.buttonBackToSources.setOnClickListener {
            /**
             * This triggers the active observer in in [com.example.headlines.features.articles.activities.ArticlesActivity]
             * causing it to <code> finish() </code> the activity
             */
            destinationViewModel.finish()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        /**
         * Avoid the memory leak
         */
        _binding = null
    }

}