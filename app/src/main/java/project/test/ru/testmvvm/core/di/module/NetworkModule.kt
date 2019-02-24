package project.test.ru.testmvvm.core.di.module

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import project.test.ru.testmvvm.feature.data.api.Api
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton



@Module
class NetworkModule {

    @Provides
    @Singleton
    internal fun provideRetrofit(): Api {
        return Retrofit.Builder()
            .baseUrl(API_PATH)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(Api::class.java)
    }

    companion object {
        private const val API_PATH = "https://api.github.com"
    }

}