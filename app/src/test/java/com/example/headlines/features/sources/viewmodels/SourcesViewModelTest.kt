package com.example.headlines.features.sources.viewmodels

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.headlines.ViewModelTest
import com.example.headlines.features.sources.models.Source
import com.example.headlines.features.sources.models.SourcesResponse
import com.example.headlines.features.sources.repositories.SourcesRepository
import com.example.headlines.features.sources.states.SourcesScreenState
import com.example.headlines.features.sources.statuses.FetchSourcesStatus
import com.example.headlines.testutils.TestDispatcherProvider
import com.example.headlines.testutils.getOrAwaitValue
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

@ExperimentalCoroutinesApi
class SourcesViewModelTest: ViewModelTest(){
    private lateinit var viewModel: SourcesViewModel

    @Mock
    lateinit var repo: SourcesRepository

    private val dataSource = MediatorLiveData<FetchSourcesStatus>()

    private val dispatcher = coroutinesTestRule.testDispatcher

    @Before
    override fun setUp() {
        super.setUp()
        whenever(repo.dataSource).thenReturn(dataSource)
    }

    @Test
    fun verifyLandingState() {
        dispatcher.resumeDispatcher()
        /**
         * As initialization calls [SourcesViewModel.fetchSources] which changes the state from landing to loading.
         */
        viewModel = SourcesViewModel(repo, TestDispatcherProvider(dispatcher))
        assertEquals(
            SourcesScreenState.Loading,
            viewModel.sourceScreenState.value
        )
    }

    @Test
    fun verifyFailure(){
        dispatcher.runBlockingTest {
            whenever(repo.fetchAllSources()).then {
                dataSource.postValue(
                    FetchSourcesStatus.Failure
                )
            }
            whenever(repo.localSource()).thenReturn(MutableLiveData<List<Source>>())
            viewModel = SourcesViewModel(repo, TestDispatcherProvider(dispatcher))
            viewModel.fetchSources()
            resumeDispatcher()
            assertEquals(
                SourcesScreenState.SourcesFetchFailed,
                viewModel.sourceScreenState.getOrAwaitValue()
            )
        }

    }

    @Test
    fun verifySuccess(){
        dispatcher.runBlockingTest {
            whenever(repo.fetchAllSources()).then {
                dataSource.postValue(
                    FetchSourcesStatus.Success(SourcesResponse(listOf(),""))
                )
            }
            /**
             * We need at least one element as the check is for [List.isNullOrEmpty]
             */
            whenever(repo.localSource()).thenReturn(MutableLiveData<List<Source>>(listOf(Source(id = "12", name = "The Washington Post"))))
            viewModel = SourcesViewModel(repo, TestDispatcherProvider(dispatcher))
            viewModel.fetchSources()
            resumeDispatcher()
            assertTrue(
                viewModel.sourceScreenState.getOrAwaitValue() is SourcesScreenState.SourcesFetched
            )
        }

    }
}