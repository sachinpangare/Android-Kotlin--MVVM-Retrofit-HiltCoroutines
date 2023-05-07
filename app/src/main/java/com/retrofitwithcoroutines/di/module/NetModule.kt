package com.retrofitwithcoroutines.di.module

import android.util.Log
import com.retrofitwithcoroutines.BuildConfig
import com.retrofitwithcoroutines.data.api.ApiHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetModule {


    @Singleton
    @Provides
    fun provideBaseUrl()= BuildConfig.BASE_URL

    @Singleton
    @Provides
    fun providesOkHttpClient() = if (BuildConfig.DEBUG)
    {
        val TAG ="Weather Demo"
        val loggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger{
            override fun log(message: String) {
                Log.e(TAG,message)
            }
        })
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    }else
    {
        OkHttpClient.Builder().build()
    }


    @Singleton
    @Provides
    fun getRetrofit(baseUrl : String,okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    @Singleton
    @Provides
    fun provideApiHelper(retrofit: Retrofit) = retrofit.create(ApiHelper::class.java)
}