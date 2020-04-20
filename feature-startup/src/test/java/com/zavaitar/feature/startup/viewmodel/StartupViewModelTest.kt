package com.zavaitar.feature.startup.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class StartupViewModelTest {

    @Mock
    lateinit var observerSingleEvent: Observer<Void>

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var startupViewModel: StartupViewModel

    @Before
    fun setup() {
      startupViewModel = StartupViewModel()
    }

    @Test
    fun `when initialize the viewModel trigger splashDidLoad event`() {
        verify(observerSingleEvent, never()).onChanged(null)

        startupViewModel.splashDidLoadEvent.observeForever(observerSingleEvent)
        startupViewModel.init()
        verify(observerSingleEvent).onChanged(null)
    }
}