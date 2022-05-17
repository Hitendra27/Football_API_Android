package com.example.football_api.di

import com.example.football_api.network.FootballAPI
import com.example.football_api.network.FootballTeamRepository
import com.example.football_api.network.FootballTeamRepositoryImpl
import com.example.football_api.viewmodel.FootballTeamViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    // provides the football repository implementation
    fun provideWeatherRepo(footballAPI: FootballAPI): FootballTeamRepository = FootballTeamRepositoryImpl(footballAPI)

    // provide Gson object
    fun provideGson() = GsonBuilder().create()

    // provide logging interceptor
    fun provideLoggingInterceptor() =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    // provide okhttp client
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor) =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

    // providing the retrofit builder
    fun provideFootballTeamApi(okHttpClient: OkHttpClient, gson: Gson): FootballAPI =
        Retrofit.Builder()
            .baseUrl(FootballAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
            .create(FootballAPI::class.java)

    single { provideGson() }
    single { provideLoggingInterceptor() }
    single { provideOkHttpClient(get()) }
    single { provideFootballTeamApi(get(), get()) }
    single { provideWeatherRepo(get()) }
}

val viewModelModule = module {
    viewModel {
        FootballTeamViewModel(get())
    }
}