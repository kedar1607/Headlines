package com.example.headlines.features.sources.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.headlines.features.sources.repositories.SourcesRepository
import com.example.headlines.features.sources.states.SourcesScreenState
import com.example.headlines.features.sources.statuses.FetchSourcesStatus
import com.example.headlines.utils.coroutines.DispatcherProvider
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * This view model is responsible for making the fetch call through [repository].
 * Basically [_sourceScreenState] or publicly available [sourceScreenState] is used to track the actual state of the UI/ View (Fragment)
 * Everything happens when the VM is initialized.
 * dispatcherProvider is used to launch the coroutine in that specific [kotlinx.coroutines.CoroutineDispatcher].
 * Please read [com.example.headlines.utils.coroutines.DispatcherProviderImpl] for more details on how these dispatchers are provided.
 */
class SourcesViewModel @Inject constructor(private val repository: SourcesRepository, private val dispatcherProvider: DispatcherProvider): ViewModel() {

    /**
     * This live data represents the overall state of the fragment that is attached to this vm
     */
    private val _sourceScreenState: MediatorLiveData<SourcesScreenState> = MediatorLiveData()
    val sourceScreenState: LiveData<SourcesScreenState> = _sourceScreenState

    init {
        /**
         * Immediately after initialization, we set the state of the screen to Landing.
         */
        _sourceScreenState.postValue(SourcesScreenState.Landing)
        _sourceScreenState.addSource(repository.dataSource){
            when(it){
                /**
                 * We only need to know if the network call failed.
                 * Success is handled from the local data source as the local data source is the single version of the truth every single time.
                 */
                FetchSourcesStatus.Failure -> _sourceScreenState.postValue(SourcesScreenState.SourcesFetchFailed)
            }
        }

        _sourceScreenState.addSource(repository.localSource()) {
            when {
                it.isNullOrEmpty() -> {
                    /**
                     * We can do this here or we can give full control to the fragment in terms of when to make the [repository] call.
                     */
                    fetchSources()
                }
                else -> _sourceScreenState.postValue(SourcesScreenState.SourcesFetched(it))
            }
        }
    }

    /**
     * [dispatcherProvider] is used to launch the coroutine in that specific [kotlinx.coroutines.CoroutineDispatcher].
     * Please read [com.example.headlines.utils.coroutines.DispatcherProviderImpl] for more details on how these dispatchers are provided.
     */
    private fun fetchSources() {
        viewModelScope.launch(dispatcherProvider.io) {
            _sourceScreenState.postValue(SourcesScreenState.Loading)
            repository.fetchAllSources()
        }
    }
}