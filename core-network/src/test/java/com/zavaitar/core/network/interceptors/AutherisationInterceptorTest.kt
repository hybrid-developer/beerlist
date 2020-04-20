package com.zavaitar.core.network.interceptors

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class AutherisationInterceptorTest {

    @Mock lateinit var interceptorChain: Interceptor.Chain
    @Mock lateinit var response: Response

    private lateinit var request: Request
    private lateinit var interceptor: AuthorisationInterceptor

    @Before
    fun setup() {
        interceptor = AuthorisationInterceptor()
    }

    @Test
    fun `Authorization header is set`() {
        request = Request.Builder().url("http://acme.com")
            .addHeader(AUTHORISATION_HEADER_KEY, "autharization")
            .build()
        whenever(interceptorChain.request()).thenReturn(request)
        whenever(interceptorChain.proceed(any())).thenReturn(response)

        interceptor.intercept(interceptorChain)
        assertThat(request.header(AUTHORISATION_HEADER_KEY)).isNotNull()
    }

    @Test
    fun `Authorization header is not set`() {
        request = Request.Builder().url("http://acme.com")
            .build()
        whenever(interceptorChain.request()).thenReturn(request)
        whenever(interceptorChain.proceed(any())).thenReturn(response)

        interceptor.intercept(interceptorChain)
        assertThat(request.header(AUTHORISATION_HEADER_KEY)).isNull()
    }

    @Test
    fun `Content-Type header is set`() {
        request = Request.Builder().url("http://acme.com")
            .addHeader(CONTENT_TYPE_HEADER_KEY, "application/json")
            .build()
        whenever(interceptorChain.request()).thenReturn(request)
        whenever(interceptorChain.proceed(any())).thenReturn(response)

        interceptor.intercept(interceptorChain)
        assertThat(request.header(CONTENT_TYPE_HEADER_KEY)).isNotNull()
    }

    @Test
    fun `Content-Type header is not set`() {
        request = Request.Builder().url("http://acme.com")
            .build()
        whenever(interceptorChain.request()).thenReturn(request)
        whenever(interceptorChain.proceed(any())).thenReturn(response)

        interceptor.intercept(interceptorChain)
        assertThat(request.header(CONTENT_TYPE_HEADER_KEY)).isNull()
    }

    private companion object {
        private const val AUTHORISATION_HEADER_KEY = "Authorization"
        private const val CONTENT_TYPE_HEADER_KEY = "Content-Type"
    }
}