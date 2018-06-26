package com.lokal.lokaltest.webservice;

import com.github.aurae.retrofit2.LoganSquareConverterFactory;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

/**
 * Created by ADMIN on 25-06-2018.
 */

public class ApiClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if(retrofit == null){
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();
                    Request request = original.newBuilder().build();

                    return chain.proceed(request);
                }
            });
            httpClient.addInterceptor(logging);
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://picsum.photos/")
                    .client(httpClient.build())
                    .addConverterFactory(LoganSquareConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
