package com.lokal.lokaltest.webservice;

import com.lokal.lokaltest.model.response.ImageResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by ADMIN on 26-06-2018.
 */

public interface ApiInterface {
    @GET("list")
    Call<List<ImageResponse>> getImageList();

    @GET
    @Streaming
    Call<ResponseBody> downloadFile(@Url String fileUrl);
}
