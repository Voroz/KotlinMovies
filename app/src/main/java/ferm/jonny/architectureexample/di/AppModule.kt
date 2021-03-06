package ferm.jonny.architectureexample.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ferm.jonny.core.Constants
import ferm.jonny.feature_movies_data.data_source.AuthInterceptor
import ferm.jonny.feature_movies_data.data_source.MovieDbApi
import ferm.jonny.feature_movies_data.repository.MovieRepositoryImpl
import ferm.jonny.feature_movies_domain.repository.MovieRepository
import ferm.jonny.feature_movies_domain.use_case.GetMovieDetails
import ferm.jonny.feature_movies_domain.use_case.GetMovies
import okhttp3.Interceptor
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
    fun provideInterceptor() : Interceptor {
        return AuthInterceptor()
    }

    @Provides
    @Singleton
    fun provideMovieDbApi(authInterceptor: Interceptor) : MovieDbApi {
        // Create interceptor, add authentication headers
        val client = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()

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

    @Provides
    @Singleton
    fun provideGetMoviesUseCase(@ApplicationContext context: Context, movieRepository: MovieRepository) : GetMovies {
        return GetMovies(context, movieRepository)
    }

    @Provides
    @Singleton
    fun provideGetMovieDetailsUseCase(@ApplicationContext context: Context, movieRepository: MovieRepository) : GetMovieDetails {
        return GetMovieDetails(context, movieRepository)
    }
}