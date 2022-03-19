package ferm.jonny.architectureexample.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ferm.jonny.architectureexample.core.Constants
import ferm.jonny.architectureexample.features.movies.data.data_source.MovieDbApi
import ferm.jonny.architectureexample.features.movies.data.repository.MovieRepository
import ferm.jonny.architectureexample.features.movies.data.repository.MovieRepositoryImpl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMovieDbApi() : MovieDbApi {
        // Create interceptor, add authentication headers
        val client = OkHttpClient.Builder().addInterceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer ${Constants.movieDbAuthToken}")
                .build()
            chain.proceed(newRequest)
        }.build()

        return Retrofit.Builder()
            .baseUrl(Constants.movieDbBaseUrl)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(MovieDbApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(api: MovieDbApi) : MovieRepository {
        return MovieRepositoryImpl(api)
    }
}