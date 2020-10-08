package com.example.headlines.features.articles.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.headlines.R
import com.example.headlines.databinding.ActivityAcrticlesBinding
import com.example.headlines.features.articles.destinations.ArticlesActivityDestination
import com.example.headlines.features.articles.fragments.ArticlesFragmentDirections
import com.example.headlines.features.articles.viewmodels.ArticlesDestinationViewModel
import com.example.headlines.utils.constants.SOURCE_ID
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

/**
 *
 * This is the hosting activity for all of the screens in the articles feature.
 */
class ArticlesActivity : AppCompatActivity(), HasAndroidInjector {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    private lateinit var navController: NavController

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: ActivityAcrticlesBinding

    private val viewModel: ArticlesDestinationViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        /*
            make sure this is called before the super.onCreate() as we need all of the dependencies in place before calling methods on super class.
         */
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityAcrticlesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        initNavigation()
        observeDestinationChanges()
        extractSourceIdFromIntent()
    }

    private fun observeDestinationChanges() {
        viewModel.destinations.observe(this, Observer { destination ->
            when (destination) {
                is ArticlesActivityDestination.StartUp -> {
                    /*
                        Stubbed: Do nothing as start destination is already selected in the navigation graph.
                     */
                }
                ArticlesActivityDestination.InvalidSource -> navController.navigate(ArticlesFragmentDirections.actionArticlesFragmentToInvalidSourceFragment())
                is ArticlesActivityDestination.Details -> TODO()
                ArticlesActivityDestination.Finish -> finish()
            }
        })
    }

    private fun extractSourceIdFromIntent() {
        intent.extras?.getString(SOURCE_ID)?.also { sourceId ->
            viewModel.startUp(sourceId)
        } ?: viewModel.invalidSourceId()
    }

    private fun initNavigation() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        val appBarConfig = AppBarConfiguration.Builder().build()
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfig)
    }
}