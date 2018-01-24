package com.jankrav.learnretrofitusingfragmens.client;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static Object instance;
    private static final String BASE_URL = "https://api.github.com";
    private static final Class<GitHubClient> DEFAULT_SERVICE_CLIENT = GitHubClient.class;

    private ServiceGenerator(){}

    private static <S> S createService(Class<S> serviceClass) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder()
                        .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                        .build())
                .build()
                .create(serviceClass);
    }

    public static synchronized GitHubClient getDefaultService() {
        if(instance == null)
            instance = createService(DEFAULT_SERVICE_CLIENT);
        return (GitHubClient) instance;
    }




}
