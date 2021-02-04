package com.example.wheretoeat.di

import com.example.wheretoeat.data.api.RatParkApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

//turns it into dagger module, the module is only there for instructions for dagger
@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit():Retrofit=
        Retrofit.Builder()
            .baseUrl(RatParkApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideRatParkApi(retrofit: Retrofit):RatParkApi=
        retrofit.create(RatParkApi::class.java)
}