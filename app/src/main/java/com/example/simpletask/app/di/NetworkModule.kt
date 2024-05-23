package com.example.simpletask.app.di


import com.example.simpletask.app.constants.AppConstants.BASE_URL
import com.example.simpletask.app.constants.AppConstants.SOCKET_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.socket.client.IO
import io.socket.client.Socket

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import java.io.IOException

import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {
        var loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain: Interceptor.Chain ->
                try {
                    val originalRequest = chain.request()
                    val builder = originalRequest.newBuilder()
                    builder.addHeader("Content-Type", "application/json")
                    val newRequest = builder.build()
                    val response = chain.proceed(newRequest)
                    response
                } catch (e: IOException) {
                    throw e

                }
            }

            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            //    .baseUrl(BuildConfig.BASE_URL)
            .baseUrl(BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun provideSocket(): Socket {
        return IO.socket(SOCKET_URL)
    }


}