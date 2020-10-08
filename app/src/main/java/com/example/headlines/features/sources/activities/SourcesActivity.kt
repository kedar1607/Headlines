package com.example.headlines.features.sources.activities

import android.content.Intent
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
import com.example.headlines.databinding.ActivitySourcesBinding
import com.example.headlines.features.articles.activities.ArticlesActivity
import com.example.headlines.features.sources.destinations.SourcesActivityDestination
import com.example.headlines.features.sources.viewmodels.SourcesDestinationViewModel
import com.example.headlines.utils.constants.SOURCE_ID
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

/**
 *
 * This is the hosting activity for all of the screens (currently only 1) in the sources feature.
 */
class SourcesActivity : AppCompatActivity(), HasAndroidInjector {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    private lateinit var navController: NavController

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: ActivitySourcesBinding

    private val viewModel: SourcesDestinationViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        /*
            make sure this is called before the super.onCreate() as we need all of the dependencies in place before calling methods on super class.
         */
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivitySourcesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        initNavigation()
        viewModel.destinations.observe(this, Observer { sourceDestination ->
            when(sourceDestination){
                is SourcesActivityDestination.Articles -> {
                    val intent = Intent(this, ArticlesActivity::class.java).also {
                        it.putExtra(SOURCE_ID, sourceDestination.sourceId)
                    }
                    startActivity(intent)
                }
            }
        })
    }

    private fun initNavigation() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        val appBarConfig = AppBarConfiguration.Builder().build()
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfig)
    }
}