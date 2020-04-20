package com.zavaitar.core.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/*
    This is not used in this assignment.
    I added this here to demonstrate how to add Authorisation headers if we need.
 */
internal class AuthorisationInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        val authorisationToken = AUTHORISATION_TOKEN
        if (authorisationToken.isNotEmpty() && chain.request()
                .header(AUTHORISATION_HEADER_KEY) == null
        ) {
            builder.addHeader(AUTHORISATION_HEADER_KEY, "Bearer $authorisationToken")
            builder.addHeader(CONTENT_TYPE_HEADER_KEY, CONTENT_TYPE_HEADER_VALUE)
        }

        return chain.proceed(builder.build())
    }

    private companion object {
        private const val AUTHORISATION_HEADER_KEY = "Authorization" // 'z' spelling deliberate
        private const val CONTENT_TYPE_HEADER_KEY = "Content-Type"
        private const val CONTENT_TYPE_HEADER_VALUE = "application/json"

        // As a best practice autharization token should be taken from the service and should not be hard coded,
        // But due to limited service availability, I'm hardcoding this only for this project purpose.
        private const val AUTHORISATION_TOKEN =
            "AAAAAAAAAAAAAAAAAAAAAF7w0wAAAAAAb6kdTQSU%2F5EmGAMD917iN7rZuVE%3D9ssQYqmHSgDTUgLDOW3k155qYVOxrGUaDSeOrW3IqvHeSoRYiu"
    }
}
