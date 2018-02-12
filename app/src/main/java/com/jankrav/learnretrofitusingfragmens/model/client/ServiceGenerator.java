package com.jankrav.learnretrofitusingfragmens.model.client;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static final String BASE_URL = "https://api.github.com";
    private static final Class<GitHubService> DEFAULT_SERVICE_CLIENT = GitHubService.class;

    private <S> S createService(Class<S> serviceClass) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder()
                        .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                        .build())
                .build()
                .create(serviceClass);
    }

    public GitHubService getDefaultService(){
        return createService(DEFAULT_SERVICE_CLIENT);
    }
}
