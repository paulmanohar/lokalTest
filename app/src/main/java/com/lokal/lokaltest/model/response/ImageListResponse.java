package com.lokal.lokaltest.model.response;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ADMIN on 26-06-2018.
 */

@JsonObject(fieldDetectionPolicy = JsonObject.FieldDetectionPolicy.NONPRIVATE_FIELDS_AND_ACCESSORS)
public class ImageListResponse {

    @JsonField(name = "imageResponseList")
    public List<ImageResponse> imageResponseList = null;

    public ImageListResponse(){

    }

    public void addImageResponse(ImageResponse imageResponse){
        List<ImageResponse> imageResponseList = new ArrayList<>();
        imageResponseList.add(imageResponse);
    }
}
