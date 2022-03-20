package ferm.jonny.architectureexample.features.movies.data.data_source

import ferm.jonny.architectureexample.core.Constants
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer ${Constants.movieDbAuthToken}")
            .build()

        return chain.proceed(newRequest)
    }
}