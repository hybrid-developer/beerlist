package com.zavaitar.core.viewmodel

import androidx.lifecycle.ViewModel
import com.nhaarman.mockito_kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import javax.inject.Provider

@RunWith(MockitoJUnitRunner::class)
class ViewModelFactoryTest {

    @Mock lateinit var provider: Provider<ViewModel>
    @Mock lateinit var providers: HashMap<Class<out ViewModel>, Provider<ViewModel>>

    private lateinit var viewModelFactory: ViewModelFactory

    @Before
    fun setup() {
        viewModelFactory = ViewModelFactory(providers)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `when getting a viewmodel with a known provider it throws an IllegalArgumentException`() {
        val expectedViewModel = CircleViewModel()
        providers[CircleViewModel::class.java] = provider

        val actualViewModel = viewModelFactory.create(CircleViewModel::class.java)

        assertThat(expectedViewModel).isEqualTo(actualViewModel)
    }

    @Test(expected = RuntimeException::class)
    fun `when getting a viewmodel with a known provider that throws RuntimeException`() {
        whenever(provider.get()).thenThrow(RuntimeException())
        providers.put(CircleViewModel::class.java, provider)
        viewModelFactory.create(CircleViewModel::class.java)
    }
}

private open class ShapeViewModel : ViewModel()
private class CircleViewModel : ShapeViewModel()
